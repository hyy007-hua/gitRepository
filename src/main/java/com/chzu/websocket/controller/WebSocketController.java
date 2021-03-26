package com.chzu.websocket.controller;

import com.chzu.util.JSONResult;
import com.chzu.websocket.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("websocket")
public class WebSocketController {

    @Bean//这个注解会从Spring容器拿出Bean
    public MyWebSocketHandler infoHandler() {
        return new MyWebSocketHandler();
    }

    @PostMapping("sendMessage")
    @ResponseBody
    public JSONResult sendMessage(){
        return JSONResult.ok();
    }
}
