package com.example.store.controller;

import com.example.store.common.config.UserPrincipal;
import com.example.store.domain.User;
import com.example.store.request.user.UserCreate;
import com.example.store.response.UserResponse;
import com.example.store.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserViewController {
    private final UserService userService;
    @GetMapping("/login-form")
    public String getLoginView(){
        return "auth/login";
    }

    @GetMapping("/signup")
    public String getSignUpView(Model model, UserCreate userCreate){
        model.addAttribute("userCreate",userCreate);
        return "auth/signup";
    }

    @GetMapping("/mypage")
    @PreAuthorize("hasRole('USER')")
    public String getMain(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getUserId();

        User user = userService.get(userId);
        UserResponse userResponse = UserResponse.fromEntity(user);
        model.addAttribute("user",userResponse);

        return "user/mypage";
    }
}
