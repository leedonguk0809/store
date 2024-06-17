package com.example.store.repository;

import com.example.store.domain.User;
import com.example.store.repository.mapper.UserMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryImplTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    public void clean(){
        userMapper.deleteAll();
    }

    @Test
    @DisplayName("User 객체를 통해서 DB에 사용자 정보를 저장할 수 있다.")
    public void test1(){
        User user = User.builder()
                .email("kimdodo@naver.com")
                .password("1234")
                .name("김도도")
                .zipcode("123-123")
                .mainAddress("부산광역시")
                .detailAddress("스파로스")
                .telNumber("01012341234")
                .build();
        //when
        User result = userRepository.save(user);

        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
    }
}