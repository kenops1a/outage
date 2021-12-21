package com.rat.web.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 10:13
 */
@Component
@ServerEndpoint(value = "/outage/one")
public class WebSocketServer {


    /**
     * 记录当前在线人数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineCount.incrementAndGet(); // 在线数加1
        System.out.println("有新连接加入" + session.getId() + "当前在线人数" + onlineCount);
    }

    /**
     * 连接关闭调用方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
        System.out.println("有连接关闭" + session.getId() + "当前人数" + onlineCount.get());
    }

    /**
     * 接收消息后调用方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("服务器接受到" + session.getId() + "的消息" + message);
        this.sendMessage(message, session);
    }

    /**
     * 发生错误的回调
     */
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            System.out.println("服务器发送消息" + message + "给客户端" + toSession.getId());
            toSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            System.out.println("消息发送失败");
        }
    }

}
