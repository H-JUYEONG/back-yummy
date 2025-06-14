package com.javaex.sms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.javaex.user.model.JuAnniversaryVo;
import com.javaex.user.repository.JuUserDao;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class JuSmsService {

	@Autowired
	private JuUserDao juDao;

	@Value("${twilio.messaging-service-sid}")
	private String messagingServiceSid;

	@Value("${twilio.phone-number}")
	private String twilioPhoneNumber;

	// 인증번호 저장소
	private final Map<String, String> verificationCodeStorage = new HashMap<>();

	// 인증번호 생성 및 전송
	public boolean sendVerificationCode(String phoneNumber) {
		// 전화번호를 국제 형식으로 변환
		String formattedPhoneNumber = formatPhoneNumber(phoneNumber);

		// 인증번호 생성
		String verificationCode = generateVerificationCode();

		// 인증번호 저장
		storeVerificationCode(formattedPhoneNumber, verificationCode);

		// SMS 전송
		return sendSms(formattedPhoneNumber, "인증번호는 " + verificationCode + " 입니다.");
	}

	// SMS 전송 메서드
	private boolean sendSms(String phoneNumber, String message) {
		try {
			Message.creator(new PhoneNumber(phoneNumber), messagingServiceSid, message).create();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 인증번호 생성 메서드
	private String generateVerificationCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000); // 6자리 랜덤 숫자
		return String.valueOf(code);
	}

	// 인증번호 저장
	private void storeVerificationCode(String phoneNumber, String verificationCode) {
		verificationCodeStorage.put(phoneNumber, verificationCode);
	}

	// 인증번호 검증
	public boolean verifyCode(String phoneNumber, String inputCode) {
		// 전화번호를 국제 형식으로 변환
		String formattedPhoneNumber = formatPhoneNumber(phoneNumber);

		String storedCode = verificationCodeStorage.get(formattedPhoneNumber);
		if (storedCode != null && storedCode.equals(inputCode)) {
			verificationCodeStorage.remove(formattedPhoneNumber); // 인증 완료 후 삭제
			return true;
		}
		return false;
	}

	// 전화번호를 국제 형식으로 변환하는 메서드
	private String formatPhoneNumber(String phoneNumber) {
		if (phoneNumber.startsWith("0")) {
			return "+82" + phoneNumber.substring(1); // 앞의 '0'을 제거하고 '+82' 추가
		}
		return phoneNumber; // 이미 국제 형식일 경우 그대로 반환
	}

	// 기념일 알림 전송 로직
	public void sendNotificationsForToday() {
		// 오늘 날짜에 알림이 설정된 기념일 조회
		List<JuAnniversaryVo> notifications = juDao.getNotificationsForToday();

		for (JuAnniversaryVo notification : notifications) {
			// 전화번호 국제 형식 변환
			String formattedPhoneNumber = formatPhoneNumber(notification.getPhone());
			// 메시지 내용 생성
			String message = String.format("안녕하세요 %s님, %s 기념일이 곧 다가옵니다! 기념일 날짜: %s", notification.getName(),
					notification.getAnniversaryName(), notification.getAnniversaryDate());
			// SMS 발송
			boolean isSent = sendSms(formattedPhoneNumber, message);

			// SMS 발송 결과 저장
			juDao.insertSmsHistory(notification.getUserId(), notification.getAnniversaryId(),
					formattedPhoneNumber, message, isSent ? "성공" : "실패");
		}
	}
}