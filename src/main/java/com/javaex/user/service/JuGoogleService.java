package com.javaex.user.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.user.model.JuUserVo;

@Service
public class JuGoogleService {
	
	@Value("${google.oauth.reqURL}")
	private String reqURL;

	@Value("${google.oauth.clientId}")
	private String clientId;

	@Value("${google.oauth.clientSecret}")
	private String clientSecret;

	@Value("${google.oauth.redirectUri}")
	private String redirectUri;

	/* 구글 로그인 액세스 토큰 가져오기 */
	public String getKakaoAccessToken(String authorizeCode) throws Exception {
		String accessToken = "";
		String refreshToken = "";

		try {

			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로 설정
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요한 파라미터 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=").append(clientId);
			sb.append("&redirect_uri=").append(redirectUri);
			sb.append("&client_secret=").append(clientSecret);
			sb.append("&code=").append(authorizeCode);
			bw.write(sb.toString());
			bw.flush();

			// 응답 코드 확인
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode: " + responseCode);

			// 응답 데이터 읽기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();
			bw.close();

			// 응답 데이터 확인
			String result = response.toString();
			System.out.println("result: " + result);

			// JSON 데이터 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			// Null 체크 추가
			accessToken = jsonMap.get("access_token") != null ? jsonMap.get("access_token").toString() : null;
			refreshToken = jsonMap.get("refresh_token") != null ? jsonMap.get("refresh_token").toString() : null;

			if (accessToken == null) {
				throw new RuntimeException("액세스 토큰이 null입니다. 응답 데이터를 확인하세요.");
			}

			System.out.println("Access Token: " + accessToken);
			System.out.println("Refresh Token: " + refreshToken);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Access Token 요청 실패", e);
		}

		return accessToken;
	}

	/* 구글 유저 정보 가져오기 */
	public JuUserVo getKakaoUserProfile(String authHeader) throws Exception {
		System.out.println("authHeader: " + authHeader);
		String accessToken = authHeader.substring(7);

		// RestTemplate을 사용해 구글 API 호출
		RestTemplate restTemplate = new RestTemplate();
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);

		org.springframework.http.HttpEntity entity = new org.springframework.http.HttpEntity(headers);

		org.springframework.http.ResponseEntity<String> response = restTemplate.exchange(
				"https://www.googleapis.com/oauth2/v2/userinfo", org.springframework.http.HttpMethod.GET, entity,
				String.class);

		// JSON 응답 파싱
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response.getBody());

		String email = jsonNode.path("email").asText();
		String name = jsonNode.path("name").asText();
		String profileImageURL = jsonNode.path("picture").asText();

		System.out.println("email: " + email);
		System.out.println("name: " + name);
		System.out.println("profileImageURL: " + profileImageURL);

		// JuUserVo 객체 생성 및 값 설정
		JuUserVo userInfo = new JuUserVo();
		userInfo.setEmail(email);
		userInfo.setName(name);
		userInfo.setUser_profile_image_url(profileImageURL);

		return userInfo;
	}

}
