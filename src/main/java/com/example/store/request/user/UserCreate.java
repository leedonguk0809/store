package com.example.store.request.user;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserCreate {

    @NotBlank(message = "사용자 이메일 필수")
    private String email;
    
    @NotBlank(message = "사용자 비밀번호 필수")
    private String password;

    @NotBlank(message = "사용자 이름은 필수")
    private String name;

    private String telNumber;

    private String zipcode;

    private String mainAddress;

    private String detailAddress;

    @Builder
    public UserCreate(String email, String password, String name, String telNumber, String zipcode, String mainAddress, String detailAddress) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.telNumber = telNumber;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
    }

    public void encryptPassword(String encryptedPassword){
        this.password = encryptedPassword;
    }
}
