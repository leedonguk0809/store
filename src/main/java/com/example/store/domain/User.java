package com.example.store.domain;

import com.example.store.request.user.UserCreate;
import com.example.store.request.user.UserEdit;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private String roles;

    @Builder
    public User(Long id, String name, String email, String password, String telNumber, String zipcode, String mainAddress, String detailAddress, String userStatus, String roles) {
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

    public static User from(UserCreate userCreate, PasswordEncoder passwordEncoder){
        return User.builder()
                .name(userCreate.getName())
                .email(userCreate.getEmail())
                .password(passwordEncoder.encode(userCreate.getPassword()))
                .telNumber(userCreate.getTelNumber())
                .zipcode(userCreate.getZipcode())
                .mainAddress(userCreate.getMainAddress())
                .detailAddress(userCreate.getDetailAddress())
                .userStatus("BRONZE")
                .build();
    }

    public void edit(UserEdit userEdit){
            password = userEdit.getPassword()!=null ? userEdit.getPassword() : password;
            telNumber = userEdit.getTelNNumber();
            zipcode = userEdit.getZipcode();
            mainAddress = userEdit.getMainAddress();
            detailAddress = userEdit.getDetailAddress();
    }

    public void editStatus(String status){
        this.userStatus = status;
    }
}
