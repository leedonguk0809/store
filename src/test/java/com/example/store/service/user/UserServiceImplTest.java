package com.example.store.service.user;

import com.example.store.domain.User;
import com.example.store.domain.UserStatus;
import com.example.store.exception.UserNotFound;
import com.example.store.repository.UserRepository;
import com.example.store.repository.mapper.UserMapper;
import com.example.store.request.user.UserCreate;
import com.example.store.request.user.UserEdit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest{
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    public void clean(){
        userMapper.deleteAll();
    }

    @Test
    @DisplayName("UserCreate로 사용자 생성이 가능하다")
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

        //when
        Long savedId = userService.signup(userCreate);
        User result = userMapper.findById(savedId).orElseThrow(UserNotFound::new);
        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("123-123",result.getZipcode());
        assertEquals("부산광역시",result.getMainAddress());
        assertEquals("스파로스",result.getDetailAddress());
        assertEquals("01012341234",result.getTelNumber());
        assertEquals("BRONZE",result.getUserStatus());
    }
    @Test
    @DisplayName("이메일을 통한 이메일 중복 확인이 가능하다")
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
        //when
        Boolean result1 = userService.validateDuplicate("kimdodo@naver.com");
        Boolean result2 = userService.validateDuplicate("kimdodo22@naver.com");
        //then
        assertEquals(result1,true);
        assertEquals(result2,false);
    }

    @Test
    @DisplayName("UserEdit을 통해서 사용자 정보 수정이 가능하다")
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

        UserEdit userEdit = UserEdit.builder()
                .password("5678")
                .zipcode("456-456")
                .mainAddress("서울특별시")
                .detailAddress("종로구")
                .telNNumber("01056785678")
                .build();
        //when
        userService.edit(user.getId(), userEdit);
        User result = userMapper.findById(user.getId()).orElseThrow(UserNotFound::new);

        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("5678", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("456-456",result.getZipcode());
        assertEquals("서울특별시",result.getMainAddress());
        assertEquals("종로구",result.getDetailAddress());
        assertEquals("01056785678",result.getTelNumber());
        assertEquals("BRONZE",result.getUserStatus());
    }

    @Test
    @DisplayName("UserEdit을 통해서 수정시 비밀번호가 누락되면 기존 비밀번호가 유지된다")
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

        UserEdit userEdit = UserEdit.builder()
                .zipcode("456-456")
                .mainAddress("서울특별시")
                .detailAddress("종로구")
                .telNNumber("01056785678")
                .build();
        //when
        userService.edit(user.getId(), userEdit);
        User result = userMapper.findById(user.getId()).orElseThrow(UserNotFound::new);

        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("456-456",result.getZipcode());
        assertEquals("서울특별시",result.getMainAddress());
        assertEquals("종로구",result.getDetailAddress());
        assertEquals("01056785678",result.getTelNumber());
        assertEquals("BRONZE",result.getUserStatus());
    }

    @Test
    @DisplayName("UserStatus를 통해서 사용자 등급을 수정할 수 있다.")
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

        //when
        userService.editUserStatus(user.getId(), UserStatus.GOLD);
        User result = userMapper.findById(user.getId()).orElseThrow(UserNotFound::new);
        //then
        assertEquals("kimdodo@naver.com",result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("김도도",result.getName());
        assertEquals("123-123",result.getZipcode());
        assertEquals("부산광역시",result.getMainAddress());
        assertEquals("스파로스",result.getDetailAddress());
        assertEquals("01012341234",result.getTelNumber());
        assertEquals("GOLD",result.getUserStatus());
    }
}
