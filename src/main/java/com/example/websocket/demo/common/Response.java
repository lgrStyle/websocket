package com.example.websocket.demo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response {
    private String code;
    private String message;
    private Object data;

    public static String SUCCESS_MSG = "success";

    private Response(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    private Response(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response success(){
        return new Response(ResponseCode.SUCCESS, SUCCESS_MSG);
    }

    public static Response success(Object data){
        return new Response(ResponseCode.SUCCESS, SUCCESS_MSG, data);
    }

    public static Response failure(String message){
        return new Response(ResponseCode.ERROR, message);
    }

    public static Response failure(String message, Object data){
        return new Response(ResponseCode.ERROR, message, data);
    }

    public static Response failure(String code, String message, Object data){
        return new Response(code, message, data);
    }
}
