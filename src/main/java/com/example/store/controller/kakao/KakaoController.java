package com.example.store.controller.kakao;

import com.example.store.domain.Payment;
import com.example.store.request.kakao.KakaoPaymentRequest;
import com.example.store.request.kakao.ReadyRequest;
import com.example.store.response.kakao.ReadyResponse;
import com.example.store.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoPayService payService;
    @GetMapping("/pay/ready")
        public String ready(@ModelAttribute KakaoPaymentRequest kakaoPaymentRequest, Model model) {
        ReadyResponse readyResponse = payService.ready(kakaoPaymentRequest);
        model.addAttribute("response", readyResponse);
        return "payment/layer/ready";
    }

    @GetMapping("/approve")
    public String approve(@RequestParam("pg_token") String pgToken, @RequestParam("partner_order_id") Long orderId, Model model) {
        Payment payment = payService.approve(pgToken, orderId);
        model.addAttribute("payment", payment);
        return  "payment/approve";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "payment/layer/cancel";
    }

    @GetMapping("/fail")
    public String fail(String openType) {
        return "payment/layer/fail";
    }
}
