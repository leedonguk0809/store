package com.example.store.service;

import com.example.store.domain.Payment;
import com.example.store.repository.PaymentRepository;
import com.example.store.util.IamPortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final IamPortUtil iamportUtil;

    public void createPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public String getIamPortToken() {
        try {
            return iamportUtil.getToken();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
