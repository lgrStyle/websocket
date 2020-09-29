package com.example.websocket.demo.controller;

import com.example.websocket.demo.OriginalWebsocket;
import com.example.websocket.demo.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebsocketController {

    @RequestMapping("/sendMessage")
    public Response sendMessage(@RequestParam("message") String message){
        OriginalWebsocket.sendMessage(message);
        return Response.success();
    }
}
