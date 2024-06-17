package com.example.store.domain;

import lombok.Builder;
import lombok.Getter;

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

    @Builder
    public User(Long id, String name, String email, String password, String telNumber, String zipcode, String mainAddress, String detailAddress, String userStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.telNumber = telNumber;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.userStatus = userStatus;
    }
}
