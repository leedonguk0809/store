package com.example.store.domain;

import com.example.store.request.UserCreate;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String telNumber;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;
    private String userStatus;
    private List<String> roles;

    @Builder
    public User(Long id, String name, String email, String password, String telNumber, String zipcode, String mainAddress, String detailAddress, String userStatus, List<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.telNumber = telNumber;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.userStatus = userStatus;
        this.roles = roles;
    }

    public static User from(UserCreate userCreate){
        return User.builder()
                .name(userCreate.getName())
                .email(userCreate.getEmail())
                .password(userCreate.getPassword())
                .telNumber(userCreate.getTelNumber())
                .zipcode(userCreate.getZipcode())
                .mainAddress(userCreate.getMainAddress())
                .detailAddress(userCreate.getDetailAddress())
                .userStatus("BRONZE")
                .build();
    }
}
