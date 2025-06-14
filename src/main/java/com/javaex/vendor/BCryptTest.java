package com.javaex.vendor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
        String rawPw = "1234"; // 원본 비밀번호
        String encoded = new BCryptPasswordEncoder().encode(rawPw); // 암호화
        System.out.println("암호화된 비밀번호: " + encoded); // 결과 출력
    }
}