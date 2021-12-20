package com.rat.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @type: outage
 * @author: yaominc
 * @description: websocket配置类
 * @date: 2021/12/20 9:26
 */
@Configuration
public class WebSocketConfig {

    /**
     * websocket的配置比较简单，主要就是创建一个服务端实例，
     * 就相当于往容器中注入了一个ServerEndpointExporter实例对象。
     * @return ServerEndpointExporter实例对象
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
