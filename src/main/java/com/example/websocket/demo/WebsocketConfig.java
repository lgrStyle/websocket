package com.example.websocket.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author admin
 */
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new MyWebSocketHandler(), "/other/websocket")
                .addInterceptors(new MyHandshakeInterceptor())
                .setHandshakeHandler(new MyHandshakeHandler())
                .setAllowedOrigins("*");

        webSocketHandlerRegistry.addHandler(new MyWebSocketHandler(), "/sockjs/websocket")
                .addInterceptors(new MyHandshakeInterceptor())
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Bean
    public ServerEndpointExporter getServerEndpointExporter(){
        return new ServerEndpointExporter();
    }


}
