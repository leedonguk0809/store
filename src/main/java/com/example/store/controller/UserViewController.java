package com.example.store.controller;

import com.example.store.request.user.UserCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserViewController {
    @GetMapping("/login-form")
    public String getLoginView(){
        return "auth/login";
    }

    @GetMapping("/signup")
    public String getSignUpView(Model model, UserCreate userCreate){
        model.addAttribute("userCreate",userCreate);
        return "auth/signup";
    }
}
