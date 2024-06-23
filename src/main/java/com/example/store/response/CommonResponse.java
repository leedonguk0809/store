package com.example.store.response;

public class CommonResponse {
    boolean success;
    int code;
    String message;

    public CommonResponse(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
