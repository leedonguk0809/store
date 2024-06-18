package com.example.store.controller;

import com.example.store.exception.AlreadyExistsEmail;
import com.example.store.request.user.UserCreate;
import com.example.store.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@ModelAttribute @Valid UserCreate userCreate){
        String email = userCreate.getEmail();
        if (userService.validateDuplicate(email)){
            throw new AlreadyExistsEmail();
        }

        Long userId = userService.signup(userCreate);
        return ResponseEntity
                .ok()
                .body(null);
    }
}
