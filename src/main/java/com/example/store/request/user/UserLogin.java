package com.example.store.request.user;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLogin {
    @NotBlank(message = "로그인 이메일 필수")
    private String email;
    
    @NotBlank(message = "로그인 비밀번호 필수")
    private String password;

    @Builder
    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}