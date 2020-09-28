package com.example.websocket.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MyWebSocketHandler extends AbstractWebSocketHandler {
    private static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public MyWebSocketHandler() {

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
        log.info("afterConnectionEstablished id={},size={}", session.getId(), getOnlineCount());
        TextMessage message = new TextMessage("来啊快活啊");
        session.sendMessage(message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessage id={},message={}", session.getId(), message);
        session.sendMessage(message);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        log.info("handleBinaryMessage id={},message={}", session.getId(), message);
        session.sendMessage(message);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("handlePongMessage id={},message={}", session.getId(), message);
        session.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("handleTransportError id={}", session.getId());
        log.error("handleTransportError", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        log.info("afterConnectionClosed id={},reason={},size={}", session.getId(), status.toString(), getOnlineCount());
    }

    public static int getOnlineCount() {
        return sessionMap.size();
    }
}
