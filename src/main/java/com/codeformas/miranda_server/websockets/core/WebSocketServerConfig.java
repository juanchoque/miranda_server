package com.codeformas.miranda_server.websockets.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketServerConfig {
    //@Autowired
    //private WebSocketServerServiceImpl webSocketServerService;

//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry
//                .addHandler(new WebSocketServerHandlers(webSocketServerService), "/websocket")
//                .setAllowedOrigins("*")
//        //.withSockJS()
//        ;
//    }
}
