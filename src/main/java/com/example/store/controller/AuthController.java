package com.example.store.controller;

import com.example.store.request.user.UserLogin;
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

    @GetMapping("/main")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> test(){
        log.info("요청");
        return ResponseEntity
                .ok()
                .body("메인");
    }

    @GetMapping("/current-user")
    public ResponseEntity<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            // 사용자의 권한 출력
            userDetails.getAuthorities().forEach(authority -> {
                System.out.println(authority.getAuthority());
            });
            return ResponseEntity.ok("현재 사용자: " + username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다.");
        }
    }

    @GetMapping("/my-page")
    public ResponseEntity<Object> myPage(@AuthenticationPrincipal UserDetails currentUser) {
        return ResponseEntity.ok("현재 사용자: " + currentUser.getUsername());
    }
}
