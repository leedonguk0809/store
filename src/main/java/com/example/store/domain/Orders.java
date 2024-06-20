package com.example.store.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Orders {
    private Long orderId;

    @NotNull
    private Long userId;

    @NotNull
    private Long orderPrice;

    @NotEmpty
    @Size(max = 20)
    private String zipcode;

    @NotEmpty
    @Size(max = 50)
    private String mainAddress;

    @NotEmpty
    @Size(max = 50)
    private String detailAddress;

    private String orderEnroll;
    private String orderStatus;
    private int totalItemCount;

    private List<Item> items;

    @Builder
    public Orders(Long orderId, Long userId, Long orderPrice, String zipcode, String mainAddress, String detailAddress, String orderEnroll, String orderStatus, int totalItemCount) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderPrice = orderPrice;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.orderEnroll = orderEnroll;
        this.orderStatus = orderStatus;
        this.totalItemCount = totalItemCount;
    }
}
