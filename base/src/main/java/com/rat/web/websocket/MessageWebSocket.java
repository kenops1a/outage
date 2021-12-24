package com.rat.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/24 10:58
 */
@Slf4j
@Component
@ServerEndpoint("/message/socket/{email}")
public class MessageWebSocket {

    /**
     * 计算在线人数
     */
    private static final AtomicInteger CLIENT_COUNTS = new AtomicInteger(0);

    /**
     * 存储session和email
     */
    private static final Map<Session, String> CLIENTS = new HashMap<>();

    /**
     * 连接错误调用此方法
     * @param error 错误处理
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    /**
     * 连接成功调用方法
     * @param email 邮箱地址
     * @param session session会话
     */
    @OnOpen
    public void onOpen(@PathParam("email") String email, Session session) {
        // 在线人数加1
        CLIENT_COUNTS.incrementAndGet();
        // 将session存入map中
        CLIENTS.put(session, email);
        log.info("**************************************");
        log.info("连接--客户端" + CLIENTS.get(session) + "已连接");
        log.info("统计--当前在线人数" + CLIENT_COUNTS);
    }

    /**
     * 关闭连接调用方法
     * @param session session会话
     */
    @OnClose
    public void onClose(Session session) {
        log.info("离线--客户端" + CLIENTS.get(session) + "断开连接");
        // 在线人数减1
        CLIENT_COUNTS.decrementAndGet();
        // 移除map中对应的session
        CLIENTS.remove(session);
        log.info("**************************************");
    }

    /**
     * 服务器收到消息后调用此方法
     * @param session session会话
     * @param message 消息
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("消息--服务器收到" + CLIENTS.get(session) + "的消息：" + message);
        this.sendMessage(session, "服务器已收到消息");
    }

    /**
     * 单发消息
     * @param session session会话
     * @param message 消息
     * @return boolean
     */
    public boolean sendMessage(Session session, String message) {
        // 遍历map，选择需要发送消息的session
        for (Map.Entry<Session, String> entry : CLIENTS.entrySet()) {
            // 判断当前entry的key session是否与传入参数session一致，如果一致则发送消息message
            if (entry.getKey().getId().equals(session.getId())) {
                try {
                    // 发送message
                    session.getBasicRemote().sendText(message);
                    // 发送成功则返回true
                    return true;
                } catch (IOException e) {
                    // 如果抛出异常则说明发送失败，返回false
                    log.info("失败--发送失败，对方可能已经离线");
                }
            }
        }
        return false;
    }

//    public boolean sendMessageAll(Map<Session, String> clients, String message) {
//        // 遍历map，给map中的每个session发送消息
//        for (Map.Entry<Session, String> entry : CLIENTS.entrySet()) {
//            try {
//                // 给session发送消息
//                entry.getKey().getBasicRemote().sendText(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
