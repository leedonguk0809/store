package com.example.store.exception.item;

import com.example.store.exception.StoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ErrorResponseItem> handleStoreException(StoreException ex, WebRequest request) {
        ErrorResponseItem errorResponse = ErrorResponseItem.builder()
                .message(ex.getMessage())
                .errorCode(ex.getClass().getSimpleName())
                .validation(ex.getValidation())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseItem> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponseItem errorResponse = ErrorResponseItem.builder()
                .message(ex.getMessage())
                .errorCode("서버 오류")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}