package com.example.store.repository;

import com.example.store.domain.User;
import com.example.store.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final UserMapper userMapper;
    @Override
    public Optional<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userMapper.findByEmailAndPassword(email,password);
    }

    @Override
    public List<String> findRoleByUserId(Long userId) {
        return userMapper.findRoleByUserId(userId);
    }

    @Override
    public void saveUserRole(Long userId, String role) {
        userMapper.saveUserRole(userId,role);
    }

    @Override
    public User save(User user) {
        userMapper.save(user);
        return user;
    }

    @Override
    public Long update(User user) {
        userMapper.update(user);
        return user.getId();
    }

    @Override
    public Long updateUserStatus(User user) {
        userMapper.updateUserStatus(user);
        return user.getId();
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }
}
