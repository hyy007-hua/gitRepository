package com.chzu.websocket.config;

import com.chzu.websocket.MyWebSocketHandler;
import com.chzu.websocket.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket配置类
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/chat").addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("http://localhost");
    }
    @Bean
    public WebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }
}
