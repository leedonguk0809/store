package com.example.store.repository.mapper;

import com.example.store.domain.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clean.sql")
class CartMapperTest {
    @Autowired
    private CartMapper mapper;

    @Test
    @DisplayName("사용자 ID로 카트 조회가 가능하다.")
    void test1() throws Exception {
        //given
        Long userId = 1L;
        //when
        Cart cart = mapper.findByUserId(1L).orElseThrow(RuntimeException::new);
        //then
        assertNotNull(cart);
    }

    @Test
    @DisplayName("사용자 ID를 통해서 카트를 생성 가능하다.")
    void test2() throws Exception {
        //given
        Cart cart = Cart.builder()
                .userId(1L)
                .build();
        //when
        mapper.save(cart);
        //then
        assertNotNull(cart.getId());
    }
    
    @Test
    @DisplayName("아이템이 없는 카트를 가져온다")
    void test3() throws Exception {
        //given
        Cart cart = Cart.builder()
                .userId(2L)
                .build();
        mapper.save(cart);

        //when
        Cart result = mapper.findByUserId(2L).orElseThrow(RuntimeException::new);
        //when
        assertNotNull(cart);
    }
}