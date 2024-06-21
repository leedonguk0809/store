package com.example.store.repository.payment;

import com.example.store.domain.Payment;
import com.example.store.exception.payment.PaymentNotFound;
import com.example.store.repository.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentMapper paymentMapper;

    @Override
    public Optional<Payment> findByTid(String tid) {
        return paymentMapper.findByTid(tid);
    }

    @Override
    public Optional<Payment> findById(Long paymentId) {
        return paymentMapper.findById(paymentId);
    }

    @Override
    public Optional<Payment> findByUserId(Long userId) {
        return paymentMapper.findByUserId(userId);
    }

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return paymentMapper.findByOrderId(orderId);
    }

    @Override
    public Long save(Payment payment) {
        paymentMapper.save(payment);
        return payment.getPaymentId();
    }

    @Override
    public List<Payment> getList(LocalDate start, LocalDate end) {
        return paymentMapper.getList(start, end);
    }

    @Override
    public void deleteAll() {
        paymentMapper.deleteAll();
    }
}
