package com.rat.web.websocket;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.rat.cache.RedisUtil;
import com.rat.model.MessageModel;
import com.rat.service.MessageService;
import com.rat.web.websocket.dto.output.MsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @description: write_bug
 * @date: 2021/12/24 14:31
 */
@Slf4j
@Component
@ServerEndpoint("/message/socket/{userId}")
public class WebSocketServerController {

    /**
     * 当前在线人数
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
    /**
     * 存放所有在线的客户端
     */
    private static final Map<Integer, Session> CLIENTS = new HashMap<>();

    private MessageModel messageModel;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MessageService messageService;

    @OnOpen
    public void onOpen(@PathParam("userId") int userId, Session session) {
        // 在线用户加1
        ONLINE_COUNT.incrementAndGet();
        // 加入map
        CLIENTS.put(userId, session);
        log.info("客户端：[" + session.getId() + "]已上线");
    }

    @OnClose
    public void onClose(@PathParam("userId") int userId, Session session) {
        // 在线用户减1
        ONLINE_COUNT.decrementAndGet();
        // 加入map
        CLIENTS.remove(userId);
        log.info("客户端：[" + session.getId() + "]已离线");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("服务器收到客户端：[" + session.getId() + "]的消息" + message);
        MsgVo msgVo = JSON.parseObject(message,MsgVo.class);
        // 获取接收者session
        Session toSession = CLIENTS.get(msgVo.getToId());
        // 向接收者session发送消息，不管成功或失败都存入数据库
        if (toSession != null) {
            try {
                toSession.getBasicRemote().sendText(msgVo.getMessage());
            } catch (IOException e) {
                log.info("消息发送失败");
            }
        } else {
            log.info("用户不在线");
        }
        // 将消息存入数据库
    }
}
