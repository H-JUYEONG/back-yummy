package com.javaex.user.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.sms.service.JuSmsService;
import com.javaex.user.model.JuLoginVo;
import com.javaex.user.model.JuUserVo;
import com.javaex.user.service.JuUserService;
import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class JuUserController {

	@Autowired
	private JuUserService juUserService;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private JuSmsService smsService;

	/* 이메일 중복체크 */
	@GetMapping("/api/check/email")
	public JsonResult emailCheck(@RequestParam(value = "email") String email) {
		boolean isAvailable = juUserService.exeEmailCheck(email);

		if (isAvailable == false) { // 등록안됨
			return JsonResult.fail("중복된 이메일");
		} else { // 등록됨
			return JsonResult.success(isAvailable);
		}
	}

	/* 닉네임 중복체크 */
	@GetMapping("/api/check/nickname")
	public JsonResult nickNameCheck(@RequestParam(value = "user_nickname") String nickname) {
		boolean isAvailable = juUserService.exeNicknameCheck(nickname);

		if (isAvailable == false) { // 등록안됨
			return JsonResult.fail("중복된 닉네임");
		} else { // 등록됨
			return JsonResult.success(isAvailable);
		}

	}

	/* 유저 회원가입 */
	@PostMapping("/api/users")
	public JsonResult userSignUp(@RequestBody JuUserVo juUserVo) {

		int count = juUserService.exeUserSignUp(juUserVo);

		if (count != 1) { // 등록안됨
			return JsonResult.fail("회원등록에 실패했습니다.");
		} else { // 등록됨
			return JsonResult.success(count);
		}

	}

	@PostMapping("/api/venders")
	public ResponseEntity<?> registerVender(
			@RequestParam("email") String email,
			@RequestParam("password_hash") String passwordHash,
			@RequestParam("representative_name") String representativeName,
			@RequestParam("vender_name") String venderName,
			@RequestParam("vender_number") String venderNumber,
			@RequestParam("business_license_url") MultipartFile businessLicenseFile
	) {
		if (businessLicenseFile == null || businessLicenseFile.isEmpty()) {
			return ResponseEntity.badRequest().body("사업자등록증 파일이 비어있습니다.");
		}

		try {
			// S3 업로드
			String key = "images/" + UUID.randomUUID() + "_" + businessLicenseFile.getOriginalFilename();
			String imageUrl = s3Service.uploadFile(key, businessLicenseFile.getInputStream(),
					businessLicenseFile.getSize(), businessLicenseFile.getContentType());

			// 사용자 객체 생성
			JuUserVo juUserVo = new JuUserVo();
			juUserVo.setEmail(email);
			juUserVo.setPassword_hash(passwordHash);
			juUserVo.setRepresentative_name(representativeName);
			juUserVo.setVender_name(venderName);
			juUserVo.setVender_number(venderNumber);
			juUserVo.setBusiness_license_url(imageUrl);

			juUserService.exeVenderSignUp(juUserVo);

			return ResponseEntity.ok(JsonResult.success("회원가입이 완료되었습니다."));

		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
		}
	}


	/* 로그인 */
	@PostMapping("/api/users/login")
	public JsonResult login(@RequestBody JuLoginVo juLoginVo, HttpServletResponse response) {
		JuLoginVo authUser = juUserService.exeLogin(juLoginVo); // 로그인 처리

		if (authUser != null) {
			// 업체 계정이면서 승인되지 않은 경우
			if ("업체".equals(authUser.getType()) && !authUser.isIs_active()) {
				return JsonResult.fail("승인되지 않은 계정입니다. 관리자에게 문의하세요.");
			}

			// JWT 토큰 생성 및 반환
			JwtUtil.createTokenAndSetHeader(response, "" + authUser.getMember_id());
			return JsonResult.success(authUser);
		} else {
			// 로그인 실패
			return JsonResult.fail("아이디 또는 비밀번호가 잘못되었습니다.");
		}
	}

	/* 휴대폰번호 중복 체크 */
	@GetMapping("/api/check/phonenumber")
	public JsonResult phoneNumberCheck(@RequestParam(value = "phone_number") String phone_number) {
		boolean isAvailable = juUserService.exePhoneNumberCheck(phone_number);

		if (isAvailable == false) { // 등록안됨
			return JsonResult.fail("중복된 휴대폰번호");
		} else { // 등록됨
			return JsonResult.success(isAvailable);
		}
	}

	/* 인증번호 전송 */
	@PostMapping("/api/auth/send/code")
	public JsonResult sendCode(@RequestBody JuUserVo juUserVo) {
		System.out.println("인증번호 전송");
		System.out.println(juUserVo);
		String phoneNumber = juUserVo.getPhone_number();

		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return JsonResult.fail("휴대폰 번호를 입력해주세요.");
		}

		// 인증번호 생성 및 SMS 전송
		boolean isSent = smsService.sendVerificationCode(phoneNumber);

		if (isSent) {
			return JsonResult.success("인증번호가 전송되었습니다.");
		} else {
			return JsonResult.fail("인증번호 전송에 실패했습니다.");
		}
	}

	/* 인증번호 검증 */
	@PostMapping("/api/auth/verify/code")
	public JsonResult verifyCode(@RequestBody JuUserVo juUserVo) {
		String phoneNumber = juUserVo.getPhone_number();
		String inputCode = juUserVo.getCode();

		if (phoneNumber == null || inputCode == null) {
			return JsonResult.fail("휴대폰 번호와 인증번호를 입력해주세요.");
		}

		boolean isVerified = smsService.verifyCode(phoneNumber, inputCode);

		if (isVerified) {
			return JsonResult.success("인증이 완료되었습니다.");
		} else {
			return JsonResult.fail("인증번호가 일치하지 않습니다.");
		}
	}

	/* 기념일 문자 알림 */
	// 매일 자정에 알림 전송
//	@Scheduled(cron = "0 0 9 * * *") // 매일 자정 실행(초 분 시 일 월 요일)
//	public void sendAnniversaryNotifications() {
//		System.out.println("스케줄 실행");
//		try {
//			smsService.sendNotificationsForToday();
//		} catch (Exception e) {
//			// 예외 로그 출력
//			System.err.println("스케줄러 작업 중 오류 발생: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}

	/* 소셜 회원가입 및 로그인 */
	@PostMapping("/api/users/social/signup")
	public JsonResult socialSignUp(@RequestBody JuUserVo juUserVo, HttpServletResponse response) {
		try {
			// Step 1: 소셜 회원가입 처리
			int count = juUserService.exeSocialSignUp(juUserVo);

			if (count != 1) {
				// 회원가입 실패 응답
				return JsonResult.fail("회원등록에 실패했습니다.");
			}

			// Step 2: 회원가입 완료된 사용자 정보 가져오기
			JuLoginVo authUser = juUserService.exeSocialLogin(juUserVo.getEmail()); // 이메일로 회원 정보 가져오기

			if (authUser != null) {
				JwtUtil.createTokenAndSetHeader(response, "" + authUser.getMember_id());

				// Step 4: 성공 응답 반환 (회원 정보 포함)
				return JsonResult.success(authUser); // userNum, id, name 등 필요한 정보 반환
			} else {
				// 예외 처리: 회원 정보가 없는 경우
				return JsonResult.fail("회원가입은 성공했으나 사용자 정보를 가져올 수 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 오류 처리
			return JsonResult.fail("회원가입 처리 중 오류 발생: " + e.getMessage());
		}
	}

}
