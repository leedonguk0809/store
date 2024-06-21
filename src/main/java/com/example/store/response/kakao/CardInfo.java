package com.example.store.response.kakao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardInfo {
    private String kakaopay_purchase_corp;
    private String kakaopay_purchase_corp_code;
    private String kakaopay_issuer_corp;
    private String kakaopay_issuer_corp_code;
    private String bin;
    private String card_type;

    @Builder
    public CardInfo(String kakaopay_purchase_corp, String kakaopay_purchase_corp_code, String kakaopay_issuer_corp, String kakaopay_issuer_corp_code, String bin, String card_type) {
        this.kakaopay_purchase_corp = kakaopay_purchase_corp;
        this.kakaopay_purchase_corp_code = kakaopay_purchase_corp_code;
        this.kakaopay_issuer_corp = kakaopay_issuer_corp;
        this.kakaopay_issuer_corp_code = kakaopay_issuer_corp_code;
        this.bin = bin;
        this.card_type = card_type;
    }
}
