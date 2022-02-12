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

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
// todo       for (Map.Entry<Integer, Session> entry : CLIENTS.entrySet()) {
//            System.out.println(entry.getKey());
//            System.out.println(entry);
//        }
        // 创建提示信息对象
        JSONObject msg = new JSONObject();
        try {
            msg.append("msg", "连接成功");
            msg.append("statue", "success");
            msg.append("userId", userId);
        //  sendMessage(JSON.toJSONString(msg));
        } catch (Exception e) {
            log.info("IO异常");
        }
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

        MsgVo msgVo = JSON.parseObject(message, MsgVo.class);
        Session toSession = CLIENTS.get(msgVo.getToId());
        if (toSession != null) {
            try {
                toSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("用户当前不在线，消息将缓存，待用户上线后推送");
        }

        /*// 将message封装为msgVo对象
        MsgVo msgVo = JSON.parseObject(message,MsgVo.class);
        // 反射获取MsgVo对象的属性和值
        Field [] fields = msgVo.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            // 私有属性设置访问权限
            field.setAccessible(true);
            System.out.println(name);
        }
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
        // 将消息存入数据库*/

    }

    /**
     * 发生错误时调用的方法
     */
    @OnError
    public void onError(Throwable error) {
        log.info("websocket发生错误");
        error.printStackTrace();
    }

    /**
     * 向客户端发送消息
     * @param message 消息内容
     * @param session 会话对象
     */
    public synchronized void sendMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
