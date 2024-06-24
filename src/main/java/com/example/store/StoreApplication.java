package com.example.store;

import com.example.store.domain.Cart;
import com.example.store.domain.User;
import com.example.store.repository.mapper.CartMapper;
import com.example.store.repository.mapper.UserMapper;
import lombok.Value;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Optional;
import java.util.TimeZone;

@SpringBootApplication
public class StoreApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartMapper cartMapper;

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String adminEmail = "admin@ssgfresh.com";
        String pw = passwordEncoder.encode("1234");

        Optional<User> admin = userMapper.findByEmail(adminEmail);
        if(admin.isEmpty()){
            User user = User.builder()
                    .email(adminEmail)
                    .password(pw)
                    .name("관리자")
                    .telNumber("01012345678")
                    .userStatus("USER_GOLD")
                    .roles("ADMIN")
                    .build();
            userMapper.save(user);
            userMapper.saveUserRole(user.getId(),"ADMIN");
            Cart cart = Cart.builder()
                    .userId(user.getId()).build();
            cartMapper.save(cart);
        }
    }
}