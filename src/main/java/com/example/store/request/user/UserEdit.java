package com.example.store.request.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEdit {
    private String password;
    private String telNNumber;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;

    @Builder
    public UserEdit(String password, String telNNumber, String zipcode, String mainAddress, String detailAddress) {
        this.password = password;
        this.telNNumber = telNNumber;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
    }
}
