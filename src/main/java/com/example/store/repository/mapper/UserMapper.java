package com.example.store.repository.mapper;

import com.example.store.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    Long save(User user);
    void update(User user);
    void delete(Long id);
    void deleteAll();
}