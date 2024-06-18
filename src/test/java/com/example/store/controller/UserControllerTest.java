package com.example.store.controller;

import com.example.store.request.user.UserCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName("사용자 로그인 요청으로 로그인 가능")
    void test1() throws Exception {
        //given
        UserCreate userCreate = UserCreate.builder()
                .email("kimdodo@naver.com")
                .password("1234")
                .name("김도도")
                .zipcode("123-123")
                .mainAddress("부산광역시")
                .detailAddress("스파로스")
                .telNumber("01012341234")
                .build();

        String json = objectMapper.writeValueAsString(userCreate);
        //when
        mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }
}