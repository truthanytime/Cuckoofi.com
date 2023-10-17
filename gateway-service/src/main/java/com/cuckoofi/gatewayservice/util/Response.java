package com.cuckoofi.gatewayservice.util;

import lombok.Data;

import java.util.Date;

@Data
public class Response<T> {

    public Integer code;
    public boolean success;
    public String message;
    public Date timestamp;
    public T body;

    public Response(){

    }

    public Response(Integer code, boolean success, String message) {
        super();
        this.code = code;
        this.success = success;
        this.message = message;
        this.timestamp = new Date();
    }

    public Response(Integer code, boolean success, String message, T body) {
        super();
        this.code = code;
        this.success = success;
        this.message = message;
        this.timestamp = new Date();
        this.body = body;
    }

    public static String commonlibsStaticResponseTest() {
        return "commonlibsStaticResponseTest worked!";
    }

    public String commonlibsResponseTest() {
        return message;
    }

}