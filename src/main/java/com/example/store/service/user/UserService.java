package com.example.store.service.user;

import com.example.store.domain.User;
import com.example.store.domain.UserStatus;
import com.example.store.request.user.UserCreate;
import com.example.store.request.user.UserEdit;

public interface UserService {
    Long signup(UserCreate userCreate);
    Boolean validateDuplicate(String email);
    User get(String email, String password);
    Long edit(Long userId,UserEdit postEdit);
    Long editUserStatus(Long id, UserStatus userStatus);
    void delete(Long userId);
}
