package com.example.store.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class StoreException extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();

    public StoreException(String message) {
        super(message);
    }

    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
