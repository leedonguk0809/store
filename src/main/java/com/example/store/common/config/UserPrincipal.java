package com.example.store.common.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal extends User {

    private final Long userId;

    public UserPrincipal(com.example.store.domain.User user) {
        super(user.getEmail(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_"+user.getRoles())));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }
}
