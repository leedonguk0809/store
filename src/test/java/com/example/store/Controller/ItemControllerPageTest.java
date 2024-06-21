package com.example.store.Controller;

import com.example.store.repository.mapper.ItemMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerPageTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemMapper itemMapper;

//    @Test
//    @DisplayName("/ 로 GET 요청시 가장 최근 아이템 20개 리스트 전달")
//    void test1() throws Exception {
//        //given
//
//
//        //when
//
//
//        //then
//
//    }
}
