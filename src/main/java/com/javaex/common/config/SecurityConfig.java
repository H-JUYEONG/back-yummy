package com.javaex.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // ✅ PasswordEncoder 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Security 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (API에서는 대부분 사용 안 함)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/**"

                        ).permitAll()            // ✅ 인증 없이 접근 가능
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .formLogin(Customizer.withDefaults()); // 기본 로그인 폼 사용 (나중에 필요하면 제거 가능)

        return http.build();
    }
}
