package com.example.store.repository.mapper;

import com.example.store.domain.Cart;
import com.example.store.domain.CartItem;
import com.example.store.repository.mapper.CartItemMapper;
import com.example.store.repository.mapper.CartMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clean.sql")
class CartItemMapperTest {
    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private CartMapper cartMapper;
//    @Test
//    @DisplayName("카트 ID와 상품 ID로 장바구니 등록 상품 조회가 가능하다")
//    void test1() throws Exception {
//        //given
//        Long userId = 1L;
//        Cart cart = cartMapper.findByUserId(10L).orElseThrow(RuntimeException::new);
//        Long cartId = cart.getId();
//        Long itemId = 1L;
//
//        //when
//        Optional<CartItem> result = cartItemMapper.findByCartIdAndItemId(cartId, 1L);
//
//        //then
//        assertNotNull(result.get());
//    }

//    @Test
//    @DisplayName("카트 ID, 상품 ID, 수량을 통해서 DB에 값의 저장 가능하다")
//    void test2() throws Exception {
//        //given
//        Cart cart = cartMapper.findByUserId(1L).orElseThrow(RuntimeException::new);
//        Long cartId = cart.getId();
//        Long itemId = 4L;
//        int quantity = 10;
//
//        //when
//        cartItemMapper.save(cartId,itemId,quantity);
//        CartItem cartItem = cartItemMapper.findByCartIdAndItemId(cartId, itemId).orElseThrow(RuntimeException::new);
//        //then
//        assertNotNull(cartItem);
//        assertEquals(cart.getId(),cartItem.getCartId());
//        assertEquals(4L,cartItem.getItem().getItemId());
//        assertEquals(10,cartItem.getItemCount());
//    }
}