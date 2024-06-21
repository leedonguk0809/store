package com.example.store.repository.payment;

import com.example.store.domain.Payment;
import com.example.store.exception.payment.PaymentNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentRepositoryImplTest {
    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void clean(){
        paymentRepository.deleteAll();
    }
    @Test
    @DisplayName("Payment 객체를 통해서 DB에 값이 저장 가능하다")
    void test1() throws Exception {
        //given
        LocalDateTime date = LocalDateTime.of(2024,6,20,11,11,11);
        Payment payment = Payment.builder()
                .userId(1L)
                .orderId(1L)
                .tid("T1234567890")
                .aid("A0987654321")
                .totalAmount(10000)
                .quantity(5)
                .vat(100)
                .paymentMethod("card")
                .createdAt(date)
                .approvedAt(date)
                .build();
        //when
        paymentRepository.save(payment);
        
        //then
        assertNotNull(payment.getPaymentId());
    }

    @Test
    @DisplayName("paymentId 로 조회 가능하다")
    void test2() throws Exception {
        //given
        LocalDateTime date = LocalDateTime.of(2024,6,20,11,11,11);
        Payment payment = Payment.builder()
                .userId(2L)
                .orderId(3L)
                .tid("T1234567890")
                .aid("A0987654321")
                .totalAmount(10000)
                .quantity(5)
                .vat(100)
                .paymentMethod("card")
                .createdAt(date)
                .approvedAt(date)
                .build();
        //when
        paymentRepository.save(payment);
        Payment result = paymentRepository.findById(payment.getPaymentId()).orElseThrow(PaymentNotFound::new);
        //then
        assertEquals(2L,result.getUserId());
        assertEquals(3L,result.getOrderId());
        assertEquals("T1234567890",result.getTid());
        assertEquals("A0987654321",result.getAid());
        assertEquals(10000,result.getTotalAmount());
        assertEquals(5,result.getQuantity());
        assertEquals("card",result.getPaymentMethod());
    }

    @Test
    @DisplayName("userId 로 조회 가능하다\"")
    void test3() throws Exception {
        //given
        LocalDateTime date = LocalDateTime.of(2024,6,20,11,11,11);
        Payment payment = Payment.builder()
                .userId(2L)
                .orderId(3L)
                .tid("T1234567890")
                .aid("A0987654321")
                .totalAmount(10000)
                .quantity(5)
                .vat(100)
                .paymentMethod("card")
                .createdAt(date)
                .approvedAt(date)
                .build();
        //when
        paymentRepository.save(payment);
        Payment result = paymentRepository.findByUserId(2L).orElseThrow(PaymentNotFound::new);
        //then
        assertEquals(2L,result.getUserId());
        assertEquals(3L,result.getOrderId());
        assertEquals("T1234567890",result.getTid());
        assertEquals("A0987654321",result.getAid());
        assertEquals(10000,result.getTotalAmount());
        assertEquals(5,result.getQuantity());
        assertEquals("card",result.getPaymentMethod());

    }

    @Test
    @DisplayName("orderId로 조회 가능하다")
    void test4() throws Exception {
        //given
        LocalDateTime date = LocalDateTime.of(2024,6,20,11,11,11);
        Payment payment = Payment.builder()
                .userId(2L)
                .orderId(3L)
                .tid("T1234567890")
                .aid("A0987654321")
                .totalAmount(10000)
                .quantity(5)
                .vat(100)
                .paymentMethod("card")
                .createdAt(date)
                .approvedAt(date)
                .build();
        //when
        paymentRepository.save(payment);
        Payment result = paymentRepository.findByOrderId(3L).orElseThrow(PaymentNotFound::new);
        //then
        assertEquals(2L,result.getUserId());
        assertEquals(3L,result.getOrderId());
        assertEquals("T1234567890",result.getTid());
        assertEquals("A0987654321",result.getAid());
        assertEquals(10000,result.getTotalAmount());
        assertEquals(5,result.getQuantity());
        assertEquals("card",result.getPaymentMethod());

    }

    @Test
    @DisplayName("tid로 조회 가능하다")
    void test5() throws Exception {
        //given
        LocalDateTime date = LocalDateTime.of(2024,6,20,11,11,11);
        Payment payment = Payment.builder()
                .userId(2L)
                .orderId(3L)
                .tid("T1234567890")
                .aid("A0987654321")
                .totalAmount(10000)
                .quantity(5)
                .vat(100)
                .paymentMethod("card")
                .createdAt(date)
                .approvedAt(date)
                .build();
        //when
        paymentRepository.save(payment);
        Payment result = paymentRepository.findByTid("T1234567890").orElseThrow(PaymentNotFound::new);
        //then
        assertEquals(2L,result.getUserId());
        assertEquals(3L,result.getOrderId());
        assertEquals("T1234567890",result.getTid());
        assertEquals("A0987654321",result.getAid());
        assertEquals(10000,result.getTotalAmount());
        assertEquals(5,result.getQuantity());
        assertEquals("card",result.getPaymentMethod());
    }
}