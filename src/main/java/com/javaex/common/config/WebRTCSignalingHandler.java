package com.javaex.common.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebRTCSignalingHandler extends TextWebSocketHandler {

	private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		System.out.println("새로운 WebSocket 연결: " + session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("수신한 메시지: " + message.getPayload());

		// 브로드캐스트: 다른 클라이언트들에게 메시지 전달
		for (WebSocketSession webSocketSession : sessions) {
			if (webSocketSession.isOpen() && !webSocketSession.getId().equals(session.getId())) {
				webSocketSession.sendMessage(message);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status)
			throws Exception {
		sessions.remove(session);
		System.out.println("WebSocket 연결 종료: " + session.getId());
	}
}
