package com.javaex.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.user.model.JuLoginVo;
import com.javaex.user.model.JuUserVo;
import com.javaex.user.service.JuNaverService;
import com.javaex.user.service.JuUserService;
import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class JuNaverLoginController {

	@Autowired
	private JuUserService juUserService;

	@Autowired
	private JuNaverService juNaverService;

	/* 네이버 로그인 액세스 토큰 가져오기 */
	@PostMapping("/api/auth/naver")
	public JsonResult naverLogin(@RequestParam("authorizeCode") String authorizeCode,
			@RequestParam("state") String state) {
		
		try {
			// Access Token 가져오기
			String accessToken = juNaverService.getNaverAccessToken(authorizeCode, state);

			// 성공적으로 토큰을 가져왔을 경우
			return JsonResult.success(accessToken);

		} catch (Exception e) {
			// 예외 처리 및 에러 메시지 반환
			System.err.println("네이버 로그인 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return JsonResult.fail("네이버 로그인 실패. 관리자에게 문의하세요.");
		}
	}

	/* 네이버 유저 정보 가져오기 및 유저 체크 */
	@GetMapping("/api/users/naver/profile")
	public JsonResult getUserNaverInfo(@RequestHeader("Authorization") String authHeader, HttpServletResponse response,
			@RequestParam("provider") String user_provider) {
		try {
			// 1. 네이버 사용자 정보 가져오기
			// 헤더에 포함된 Authorization 값을 사용하여 카카오 API 호출
			// 카카오 사용자 정보를 JuUserVo 객체로 반환받음 (email, nickname 등)
			JuUserVo userInfo = juNaverService.getNaverUserProfile(authHeader);

			// 2. 가져온 이메일로 유저 체크
			// 사용자 이메일을 기준으로 데이터베이스에서 유저 정보를 조회
			// 회원가입된 사용자라면 JuLoginVo 객체로 반환, 없으면 null
			JuLoginVo authUser = juUserService.exeSocialLogin(userInfo.getEmail());

			if (authUser != null) {
				// 회원이 존재하는 경우
				if (user_provider.equals(authUser.getUser_provider())) {
					// 같은 소셜 계정으로 로그인 시도
					// JWT 토큰 생성 후 응답 헤더에 추가
					JwtUtil.createTokenAndSetHeader(response, "" + authUser.getMember_id());
					// 클라이언트로 사용자 정보(authUser)와 상태 메시지("이미 가입된 이메일") 반환
					return JsonResult.success(Map.of("authUser", authUser, "message", "이미 가입된 이메일"));
				} else {
					// 다른 소셜 계정으로 로그인 시도 (카카오 ↔ 네이버)
					// 상태 메시지("이미 가입된 이메일")만 반환
					return JsonResult.success(Map.of("message", "이미 가입된 이메일"));
				}
			}

			// 3. 회원이 존재하지 않는 경우
			// 신규 회원으로 간주하고 사용자 정보(userInfo)와 상태 메시지("신규 회원") 반환
			return JsonResult.success(Map.of("userInfo", userInfo, "message", "신규 회원"));
		} catch (Exception e) {
			return JsonResult.fail("사용자 정보 조회 실패: " + e.getMessage());
		}
	}

}
