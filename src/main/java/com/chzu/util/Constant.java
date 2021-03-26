package com.chzu.util;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述全局变量
 * onlineUser表，用userId为键，存放在线的用户WebSocketSession
 */
public class Constant {
    public static final Map<String, WebSocketSession> onlineUsers = new HashMap<String, WebSocketSession>();
}
