package com.example.store.service.user;

import com.example.store.domain.User;
import com.example.store.request.UserCreate;

public interface UserService {
    Long signup(UserCreate userCreate);
    Boolean validateDuplicate(String email);
    void delete(Long userId);
}
