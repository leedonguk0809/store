package com.example.store.repository.payment;

import com.example.store.domain.Payment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> findByTid(String tid);

    Optional<Payment> findById(Long paymentId);

    Optional<Payment> findByUserId(Long userId);

    Optional<Payment> findByOrderId(Long orderId);

    Long save(Payment payment);

    List<Payment> getList(LocalDate start, LocalDate end);

    void deleteAll();
}