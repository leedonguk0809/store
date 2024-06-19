package com.example.store.controller;

import com.example.store.domain.User;
import com.example.store.repository.mapper.UserMapper;
import com.example.store.request.user.UserCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;
    private static JdbcTemplate jdbcTemplate;
    @BeforeAll
    static void setup(@Autowired DataSource dataSource) throws Exception {
        // schema.sql 파일을 읽어와서 데이터베이스 초기화
        String schemaSql = new String(Files.readAllBytes(Paths.get("src/test/resources/schema.sql")));

        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(schemaSql);
    }
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
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", userCreate.getEmail())
                        .param("password", userCreate.getPassword())
                        .param("name", userCreate.getName())
                        .param("zipcode", userCreate.getZipcode())
                        .param("mainAddress", userCreate.getMainAddress())
                        .param("detailAddress", userCreate.getDetailAddress())
                        .param("telNumber", userCreate.getTelNumber()))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    @DisplayName("중복된 이메일로 요청시 400 응답")
    void test2() throws Exception {
        //given
        User user = User.builder()
                .email("kimdodo@naver.com")
                .password("1234")
                .name("김도도")
                .zipcode("123-123")
                .mainAddress("부산광역시")
                .detailAddress("스파로스")
                .telNumber("01012341234")
                .userStatus("BRONZE")
                .build();
        userMapper.save(user);

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
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", userCreate.getEmail())
                        .param("password", userCreate.getPassword())
                        .param("name", userCreate.getName())
                        .param("zipcode", userCreate.getZipcode())
                        .param("mainAddress", userCreate.getMainAddress())
                        .param("detailAddress", userCreate.getDetailAddress())
                        .param("telNumber", userCreate.getTelNumber()))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}