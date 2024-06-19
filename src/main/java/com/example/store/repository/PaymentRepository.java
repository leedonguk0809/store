package com.example.store.repository;

import com.example.store.domain.Payment;
import com.example.store.repository.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {

    @Autowired
    private PaymentMapper paymentMapper;

    public void save(Payment payment) {
        paymentMapper.insertPayment(payment);
    }
}
