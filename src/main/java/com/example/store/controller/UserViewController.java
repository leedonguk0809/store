package com.example.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserViewController {
    @GetMapping("/login-form")
    public String getLoginView(){
        return "auth/login";
    }

    @GetMapping("/signup")
    public String getSignUpView(){
        return "auth/signup";
    }
}
