package com.qiwei.engine.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

@Component
@ServerEndpoint("/ws/{token}")
public class WebSocketServer {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * One token per client
     */
    private String token = "";

    private static HashMap<String, Session> map = new HashMap<>();

    /**
     * Connection successful
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        map.put(token, session);
        this.token = token;
        LOG.info("New connections：token:{}，session id：{}，Number of current connections:{}", token, session.getId(), map.size());
    }

    /**
     * Connection closed
     */
    @OnClose
    public void onClose(Session session) {
        map.remove(this.token);
        LOG.info("Connection closed，token:{}，session id：{}！Number of current connections:{}", this.token, session.getId(), map.size());
    }

    /**
     * Message received
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOG.info("Message received:{},Contents:{}", token, message);
    }

    /**
     * Connection error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOG.error("error occurred", error);
    }

    /**
     * Group Messaging
     */
    public void sendInfo(String message) {
        for (String token : map.keySet()) {
            Session session = map.get(token);
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                LOG.error("Push message failure:{}，Contents:{}", token, message);
            }
            LOG.info("Push messages:{}，Contents:{}", token, message);
        }
    }

}
