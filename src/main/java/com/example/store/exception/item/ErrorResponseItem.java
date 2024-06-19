package com.example.store.exception.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseItem {
    private String message;
    private String errorCode;
    private Map<String, String> validation;

    public ErrorResponseItem(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}