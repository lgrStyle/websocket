package com.example.websocket.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint("/websocket")
@Component
public class OriginalWebsocket {

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        log.info("onOpen id={},size={}", session.getId(), getOnlineCount());
        try {
            session.getBasicRemote().sendText("欢迎光临！");
        } catch (IOException e) {
            log.error("onOpen", e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessionMap.remove(session.getId());
        log.info("onClose id={},reason={},size={}", session.getId(), closeReason.toString(), getOnlineCount());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("onMessage id={},message={}", session.getId(), message);
        try {
            session.getBasicRemote().sendText("已收到来信");
        } catch (IOException e) {
            log.error("onMessage", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("onError id={}", session.getId());
        log.error("onError", throwable);
    }

    public static int getOnlineCount() {
        return sessionMap.size();
    }
}
