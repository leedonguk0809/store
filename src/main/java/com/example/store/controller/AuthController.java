package com.example.store.controller;

import com.example.store.domain.User;
import com.example.store.request.user.UserLogin;
import com.example.store.response.UserResponse;
import com.example.store.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@ModelAttribute UserLogin login){
        log.info("{}",login);
        return ResponseEntity
                .ok()
                .body(null);
    }
    @GetMapping("/my-page")
    @PreAuthorize("hasRole('USER')")
    public String myPage(@AuthenticationPrincipal UserDetails currentUser, Model model){
        User user = userService.get(currentUser.getUsername(), currentUser.getPassword());
        UserResponse userResponse = UserResponse.fromEntity(user);
        model.addAttribute(userResponse);
        return "mypage";
    }
}
