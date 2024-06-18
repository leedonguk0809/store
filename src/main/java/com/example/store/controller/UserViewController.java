package com.example.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserViewController {
    @GetMapping("/")
    public ResponseEntity<Object> getLoginView(){
        return ResponseEntity.ok().body("로그아웃");
    }

    @PostMapping("/")
    public String getView(){
        return "login";
    }
}
