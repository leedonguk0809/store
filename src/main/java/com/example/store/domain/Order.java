package com.example.store.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order{
    private Long id;
    private Long userId;
    private Integer orderPrice;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;
    private LocalDateTime orderEnroll;
    private String orderStatus;
    private int totalItemCount;
    private String tid;

    private List<OrderItem> orderItems;

    @Builder
    public Order(Long id, Long userId, Integer orderPrice, String zipcode, String mainAddress, String detailAddress, LocalDateTime orderEnroll, String orderStatus, int totalItemCount, String tid) {
        this.id = id;
        this.userId = userId;
        this.orderPrice = orderPrice;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.orderEnroll = orderEnroll;
        this.orderStatus = orderStatus;
        this.totalItemCount = totalItemCount;
        this.tid = tid;
    }

    public void calculateOrderPrice() {
        if (orderItems != null && !orderItems.isEmpty()) {
            int totalPrice = orderItems.stream()
                    .mapToInt(orderItem -> orderItem.getItem().getPrice() * orderItem.getCount())
                    .sum();
            this.orderPrice = totalPrice;
        } else {
            this.orderPrice = 0; // 주문 상품이 없는 경우 0으로 설정
        }
    }
}