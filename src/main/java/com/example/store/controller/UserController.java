package com.example.store.controller;

import com.example.store.exception.user.AlreadyExistsEmail;
import com.example.store.request.user.UserCreate;
import com.example.store.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @PostMapping("/signup")
    public String signup(@ModelAttribute @Valid UserCreate userCreate){
        String email = userCreate.getEmail();
        if (userService.validateDuplicate(email)){
            throw new AlreadyExistsEmail();
        }

        Long userId = userService.signup(userCreate);
        return "redirect:/";
    }

    @GetMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestParam("email") String email) throws JsonProcessingException {
        boolean isDuplicate = userService.validateDuplicate(email);

        if (isDuplicate) {
            String json = objectMapper.writeValueAsString(Map.of("use", "disable"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        } else {
            String json = objectMapper.writeValueAsString(Map.of("use", "available"));
            return ResponseEntity.status(HttpStatus.OK).body(json);
        }
    }
}
