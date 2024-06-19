package com.example.store.controller;

import com.example.store.domain.Payment;
import com.example.store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/payments/complete")
    public String completePayment(@RequestParam("imp_uid") String impUid,
                                  @RequestParam("merchant_uid") String merchantUid,
                                  @RequestParam("paid_amount") Long paidAmount,
                                  @RequestParam("status") String status,
                                  Model model) {
        if ("paid".equals(status)) {
            Payment payment = new Payment();
            payment.setOrderId(Long.parseLong(merchantUid.split("_")[1]));
            payment.setPaymentPrice(paidAmount);
            paymentService.createPayment(payment);
            model.addAttribute("message", "Payment completed successfully!");
        } else {
            model.addAttribute("message", "Payment failed!");
        }
        return "result";
    }
}
