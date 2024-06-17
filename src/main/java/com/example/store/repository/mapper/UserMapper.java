package com.example.store.repository.mapper;

import com.example.store.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    List<String> findRoleByUserId(Long userId);
    Long save(User user);
    void saveUserRole(@Param("userId") Long userId, @Param("role") String role);
    void update(User user);
    void delete(Long id);
    void deleteAll();
}