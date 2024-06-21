package com.example.store.service;

import com.example.store.domain.Cart;
import com.example.store.domain.CartItem;
import com.example.store.domain.User;
import com.example.store.exception.cart.CartNotFoundException;
import com.example.store.repository.mapper.CartItemMapper;
import com.example.store.repository.mapper.CartMapper;
import com.example.store.request.cart.CartEdit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clean.sql")
class CartServiceImplTest {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @AfterEach
    public void clean(){

    }

    /*
    * 사용자 ID 1L
    * 카트 ID 1L
    * 아이템 각 4개 1,2,3,4
    * */
    @Test
    @DisplayName("사용자 ID로 카트 정보를 가져올 수 있다")
    void test1() throws Exception {
        //given
        Long userId = 1L;
        Long cartId = 1L;

        //when
        Cart cart = cartService.findByUserId(userId);

        //then
        assertNotNull(cart);
    }

    @Test
    @DisplayName("사용자 ID로 카트 유뮤를 파악할 수 있다.")
    void test2() throws Exception {
        //given
        Long userId = 1L;
        Long cartId = 1L;

        //when
        boolean userOneCart = cartService.existCart(1L);
        boolean userTwoCart = cartService.existCart(2L);

        //then
        assertTrue(userOneCart);
        assertFalse(userTwoCart);
    }

    @Test
    @DisplayName("CardEdit 클래스로 카트에 새로운 아이템을 추가 가능하다.")
    void test3() throws Exception {
        //given
        CartEdit cartEdit = CartEdit.builder()
                .itemId(4L)
                .quantity(10)
                .userId(1L)
                .build();

        //when
        cartService.addCart(cartEdit);
        Set<CartItem> cartItems = cartService.findByUserId(1L).getCartItems();
        //then
        CartItem result = cartItems.stream().
                filter(item -> item.getItem().getItemId().equals(4L))
                .findFirst()
                .orElse(null);

        assertNotNull(result);
        assertEquals(4L,result.getItem().getItemId());
        assertEquals(10,result.getItemCount());
    }

    @Test
    @DisplayName("CardEdit 로 기존의 아이템에 아이템 수량을 추가 가능하다")
    void test4() throws Exception {
        //given
        CartEdit cartEdit = CartEdit.builder()
                .itemId(1L)
                .quantity(20)
                .userId(1L)
                .build();

        //when
        cartService.addCart(cartEdit);
        Set<CartItem> cartItems = cartService.findByUserId(1L).getCartItems();
        //then
        CartItem result = cartItems.stream().
                filter(item -> item.getItem().getItemId().equals(1L))
                .findFirst()
                .orElse(null);
        //then
        assertNotNull(result);
        assertEquals(1L,result.getItem().getItemId());
        assertEquals(21,result.getItemCount());
    }

    @Test
    @DisplayName("CardEdit 로 기존의 아이템 값을 변경 가능하다")
    void test5() throws Exception {
        ///given
        CartEdit cartEdit = CartEdit.builder()
                .itemId(1L)
                .quantity(20)
                .userId(1L)
                .build();

        //when
        cartService.editQuantity(cartEdit);
        Set<CartItem> cartItems = cartService.findByUserId(1L).getCartItems();
        //then
        CartItem result = cartItems.stream().
                filter(item -> item.getItem().getItemId().equals(1L))
                .findFirst()
                .orElse(null);
        //then
        assertNotNull(result);
        assertEquals(1L,result.getItem().getItemId());
        assertEquals(20,result.getItemCount());
    }

    @Test
    @DisplayName("CardEdit에서 수량을 0으로 하면 카트에서 삭제 된다")
    void test6() throws Exception {
        //given
        CartEdit cartEdit = CartEdit.builder()
                .itemId(1L)
                .quantity(0)
                .userId(1L)
                .build();

        //when
        cartService.editQuantity(cartEdit);
        Set<CartItem> cartItems = cartService.findByUserId(1L).getCartItems();
        //expcet
        assertThrows(CartNotFoundException.class,() -> {
            cartItems.stream().
                    filter(item -> item.getItem().getItemId().equals(1L))
                    .findFirst()
                    .orElseThrow(CartNotFoundException::new);
        });
    }
}