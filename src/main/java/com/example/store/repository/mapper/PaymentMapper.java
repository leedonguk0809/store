package com.example.store.repository.mapper;

import com.example.store.domain.Payment;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentMapper {
    Optional<Payment> findByTid(@Param("tid") String tid);

    Optional<Payment> findById(@Param("paymentId") Long paymentId);

    Optional<Payment> findByUserId(@Param("userId") Long userId);
    Optional<Payment> findByOrderId(@Param("orderId") Long orderId);
    void save(Payment payment);
    List<Payment> getList(@Param("start") LocalDate start, @Param("end") LocalDate end);


    void deleteAll();
}
