package com.rat.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @type: outage
 * @author: yaominc
 * @description: 一对多，一个服务端，多个客户端
 * @date: 2021/12/20 10:55
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/oneMany/{email}")
public class WebSocketOneToMany {
    /**
     * 用户邮箱账号
     */
    private static String email;
    /**
     * 当前在线人数
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
    /**
     * 存放所有在线的客户端
     */
    private static final Map<String, Session> CLIENTS = new HashMap<>();

    /**
     * 连接成功
     * @param email 用户邮箱账号
     * @param session 客户端session
     */
    @OnOpen
    public void onOpen(@PathParam("email") String email, Session session) {
        System.out.println(CLIENTS);
        // 在线人数加1
        ONLINE_COUNT.incrementAndGet();
        // 将新上线的客户端加入clients
        CLIENTS.put(email, session);
        System.out.println("有新连接加入" + email + "当前在线人数" + ONLINE_COUNT.get());
    }
    /**
     * 连接关闭调用方法
     */
    @OnClose
    public void onClose(Session session) {
        // 在线数减1
        ONLINE_COUNT.decrementAndGet();
        // map中移除离线客户端
        CLIENTS.remove(session.getId());
        System.out.println("有连接关闭" + CLIENTS.get("email") + "当前人数" + ONLINE_COUNT.get());
    }
    /**
     * 接收消息后调用方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("服务器接受到" + CLIENTS.get(email) + "的消息" + message);
        this.sendMessage("消息已收到", session);
    }

    /**
     * 单发消息
     * @param message 消息
     * @param session 客户端session
     */
    public void sendMessage(String message, Session session) {
        try {
            for (Map.Entry<String,Session> entry : CLIENTS.entrySet()) {
                if (entry.getValue().getId().equals(session.getId())) {
                    session.getBasicRemote().sendText(message);
                }
            }
        } catch (IOException e) {
            System.out.println("消息发送失败");
        }
    }

    /**
     * 群发消息
     */
    public void sendMessage(String message, Map<String, Session> sessionMap) {
        for (Map.Entry<String, Session> entry: sessionMap.entrySet()) {
            try {
                entry.getValue().getBasicRemote().sendText(message);
            } catch (IOException e) {
                System.out.println("给客户端" + entry.getKey() + "的消息发送失败");
            }
        }
    }
}
