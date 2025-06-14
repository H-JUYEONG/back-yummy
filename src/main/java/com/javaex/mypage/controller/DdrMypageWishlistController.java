package com.javaex.mypage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;
import com.javaex.mypage.model.DdrMypageWishlistVo;
import com.javaex.mypage.service.DdrMypageWishlistService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/wishlist")
public class DdrMypageWishlistController {

	@Autowired

	private DdrMypageWishlistService ddrmypagewishlistservice;

	@GetMapping("/products")
	public JsonResult getWishlistProducts(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "6") int size, @RequestParam(required = false) String style,
			@RequestParam(required = false) String target, @RequestParam(required = false) String keyword,
			HttpServletRequest request) {

		try {
			// 요청 헤더에서 토큰 추출
			String token = JwtUtil.getTokenByHeader(request);
			if (token != null) {
				// 토큰 유효성 검사
				boolean isValid = JwtUtil.checkToken(token);
				if (isValid) {
					// 토큰에서 사용자 no 추출
					int memberId = Integer.parseInt(JwtUtil.getSubjectFromToken(token));

					DdrMypageWishlistVo vo = new DdrMypageWishlistVo();
					vo.setMemberId(memberId); // memberId 설정
					vo.setPage(page);
					vo.setSize(size);
					vo.setStyle(style);
					vo.setTarget(target);
					vo.setSearchKeyword(keyword);

					Map<String, Object> result = ddrmypagewishlistservice.exegetWishlistProducts(vo);
					return JsonResult.success(result);
				} else {
					return JsonResult.fail("유효하지 않은 토큰입니다.");
				}
			} else {
				return JsonResult.fail("토큰이 없습니다.");
			}
		} catch (Exception e) {
			return JsonResult.fail("위시리스트 조회 실패: " + e.getMessage());
		}
	}
}