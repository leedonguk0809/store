package com.example.store.repository;

import com.example.store.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    List<String> findRoleByUserId(Long userId);

    Boolean existsEmail(String email);
    void saveUserRole(Long userId, String role);
    User save(User user);
    Long update(User user);

    Long updateUserStatus(User user);
    void delete(Long id);
}
