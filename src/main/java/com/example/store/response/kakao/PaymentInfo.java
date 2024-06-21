package com.example.store.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PaymentInfo {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private String item_code;
    private String payload;
    private int quantity;
    private Amount amount;
    private String payment_method_type;
    private CardInfo card_info;
    private String sequential_payment_methods;
    private LocalDateTime created_at;
    private LocalDateTime approved_at;

    // Amount 클래스는 JSON의 amount 필드에 대응하는 내부 클래스로 정의
    @Getter
    @Builder
    public static class Amount {
        private long total;
        private long tax_free;
        private long vat;
        private long point;
        private long discount;
        private long green_deposit;
    }

    // JSON 데이터에서 LocalDateTime 형식을 파싱하기 위한 설정
    @JsonProperty("created_at")
    public void setCreated_at(String created_at) {
        this.created_at = LocalDateTime.parse(created_at);
    }

    @JsonProperty("approved_at")
    public void setApproved_at(String approved_at) {
        this.approved_at = LocalDateTime.parse(approved_at);
    }
}