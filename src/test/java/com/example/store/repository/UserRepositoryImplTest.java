package com.example.store.repository;

import com.example.store.domain.User;
import com.example.store.exception.user.UserNotFound;
import com.example.store.repository.mapper.UserMapper;
import com.example.store.repository.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
                .userStatus("BRONZE")
                .build();
        //when
        User result = userRepository.save(user);

        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
    }

    @Test
    @DisplayName("User ID를 통해서 회원을 조회 할 수 있다 ")
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
        Long saved = userMapper.save(user);

        //when
        User result = userRepository.findById(user.getId()).orElseThrow(UserNotFound::new);
        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("123-123",result.getZipcode());
        assertEquals("부산광역시",result.getMainAddress());
        assertEquals("스파로스",result.getDetailAddress());
        assertEquals("01012341234",result.getTelNumber());
        
    }

    @Test
    @DisplayName("User 이메일를 통해서 회원을 조회 할 수 있다 ")
    void test3() throws Exception {
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

        //when
        User result = userRepository.findByEmail("kimdodo@naver.com").orElseThrow(UserNotFound::new);

        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("123-123",result.getZipcode());
        assertEquals("부산광역시",result.getMainAddress());
        assertEquals("스파로스",result.getDetailAddress());
        assertEquals("01012341234",result.getTelNumber());
    }

    @Test
    @DisplayName("User 이메일과 비밀번호를 통해서 회원을 조회 할 수 있다 ")
    void test4() throws Exception {
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

        //when
        User result = userRepository.findByEmailAndPassword("kimdodo@naver.com","1234")
                .orElseThrow(UserNotFound::new);

        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("123-123",result.getZipcode());
        assertEquals("부산광역시",result.getMainAddress());
        assertEquals("스파로스",result.getDetailAddress());
        assertEquals("01012341234",result.getTelNumber());
    }
    
    @Test
    @DisplayName("User 객체를 통해서 사용자 정보를 수정 가능하다")
    void test5() throws Exception {
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

        user = User.builder()
                .id(user.getId())
                .email("kimdodo@naver.com")
                .password("5678")
                .name("김도도")
                .zipcode("456-456")
                .mainAddress("서울특별시")
                .detailAddress("종로구")
                .telNumber("01056785678")
                .userStatus("BRONZE")
                .build();

        //when
        userRepository.update(user);
        User result = userMapper.findById(user.getId()).orElseThrow(UserNotFound::new);
        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("5678", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("456-456",result.getZipcode());
        assertEquals("서울특별시",result.getMainAddress());
        assertEquals("종로구",result.getDetailAddress());
        assertEquals("01056785678",result.getTelNumber());
    }

    @Test
    @DisplayName("User Id 를 통해서 사용자 권한 정보 조회가 가능하다")
    void test6() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
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
        userMapper.saveUserRole(user.getId(),"USER");

        //when
        List<String> roles = userRepository.findRoleByUserId(user.getId());

        //then
        assertEquals(1,roles.size());
        assertEquals("USER",roles.get(0));
    }

    @Test
    @DisplayName("User 객체를 통해서 사용자 등급만 수정 가능하다")
    void test7() throws Exception {
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

        user = User.builder()
                .id(user.getId())
                .email("kimdodo@naver.com")
                .password("5678")
                .name("김도도")
                .zipcode("123-234")
                .mainAddress("부산광역시")
                .detailAddress("스파로스")
                .telNumber("01012341234")
                .userStatus("SILVER")
                .build();

        //when
        userRepository.updateUserStatus(user);
        User result = userMapper.findById(user.getId()).orElseThrow(UserNotFound::new);
        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("123-123",result.getZipcode());
        assertEquals("부산광역시",result.getMainAddress());
        assertEquals("스파로스",result.getDetailAddress());
        assertEquals("01012341234",result.getTelNumber());
        assertEquals("SILVER",result.getUserStatus());
    }
}