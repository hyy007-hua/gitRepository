package com.chzu.websocket;

import com.chzu.util.Constant;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class MyWebSocketHandler extends TextWebSocketHandler {

    Logger logger = Logger.getLogger("com.chzu.websocket.MyWebSocketHandler");


    /**
     * 收到消息时触发的回调
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        JsonParser j = new JsonParser();
        JsonElement element = j.parse(message.getPayload());
        JsonObject jsonObject = element.getAsJsonObject();
        jsonObject.get("mine");
        JsonObject mine = jsonObject.getAsJsonObject("mine");

        JsonObject to = jsonObject.getAsJsonObject("to");
        logger.info("信息发送给" + to.get("username") + "内容是：" + message.getPayload());
        sendToSinglePerson(String.valueOf(to.get("id")),new TextMessage(message.getPayload()));
    }
    /**
     * 建立连接后触发的回调
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        logger.info("已经建立websocket连接了....");
        String clientId = (String) session.getAttributes().get("clientId");
        String clientName = (String) session.getAttributes().get("clientName");
        if(clientId != null && clientId != ""){
            logger.info("用户"+clientName+"上线了...id:"+clientId);
            Constant.onlineUsers.put(clientId,session);
            logger.info("当前在线人数："+Constant.onlineUsers.size()+"人");
        }

    }

    /**
     * 传输消息出错时触发的回调
     */
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{
        logger.info("websocket连接出错...");
    }

    /**
     * 断开连接后触发的回调
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception{
        logger.info("websocket连接断开....");
        String clientId = (String) session.getAttributes().get("clientId");
        if(clientId != null && clientId != ""){
            logger.info("用户"+clientId+"下线了...");
            Constant.onlineUsers.remove(clientId);
        }
    }

    /**
     * 是否处理分片消息
     */
    public boolean supportsPartialMessages(){
        return true;
    }

    /***
     * 描述：发送消息给指定用户
     * @param clientId  用户id
     * @param message   消息内容
     * @return
     */
    public boolean sendToSinglePerson(String clientId, TextMessage message){
        //去除字符串前后的双引号
        clientId = clientId.replace("\"","");
        if (!Constant.onlineUsers.containsKey(clientId)) {
            logger.info("用户不在线,数据将存至数据库。");
            return false;
        }
        WebSocketSession session = Constant.onlineUsers.get(clientId);
        if (!session.isOpen()) {
            logger.info(clientId + "用户的session已关闭，信息将存至数据库！");
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
