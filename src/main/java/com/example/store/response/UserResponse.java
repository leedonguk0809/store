package com.example.store.response;

import com.example.store.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {
    private String name;
    private String email;
    private String telNumber;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;
    private String userStatus;

    @Builder
    public UserResponse(String name, String email, String telNumber, String zipcode, String mainAddress, String detailAddress, String userStatus) {
        this.name = name;
        this.email = email;
        this.telNumber = telNumber;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.userStatus = userStatus;
    }

    public static UserResponse fromEntity(User user){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .telNumber(user.getTelNumber())
                .zipcode(user.getZipcode())
                .mainAddress(user.getMainAddress())
                .detailAddress(user.getDetailAddress())
                .userStatus(user.getUserStatus())
                .build();
    }
}
