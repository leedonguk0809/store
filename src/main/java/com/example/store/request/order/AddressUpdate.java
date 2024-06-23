package com.example.store.request.order;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressUpdate {
    private Long orderId;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;

    @Builder
    public AddressUpdate(Long orderId, String zipcode, String mainAddress, String detailAddress) {
        this.orderId = orderId;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
    }
}
