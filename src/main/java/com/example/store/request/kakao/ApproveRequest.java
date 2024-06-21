package com.example.store.request.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApproveRequest {
    private String cid;
    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
    private String pgToken;
}
