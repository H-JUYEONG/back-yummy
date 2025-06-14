package com.javaex.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebRTCSignalingHandler webRTCSignalingHandler = new WebRTCSignalingHandler();

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webRTCSignalingHandler, "/ws")
                .setAllowedOrigins("*"); // 모든 오리진 허용
    }
}
