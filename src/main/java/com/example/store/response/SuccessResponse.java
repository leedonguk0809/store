package com.example.store.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SuccessResponse {

    private final String code;
    private final String message;

    @Builder
    public SuccessResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
