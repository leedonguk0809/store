package com.example.store.service.user;

import com.example.store.domain.User;
import com.example.store.domain.UserStatus;
import com.example.store.exception.user.InvalidPassword;
import com.example.store.exception.user.UserNotFound;
import com.example.store.repository.user.UserRepository;
import com.example.store.request.user.UserCreate;
import com.example.store.request.user.UserEdit;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public Long signup(UserCreate userCreate) {
        User user = User.from(userCreate,passwordEncoder);
        userRepository.save(user);
        userRepository.saveUserRole(user.getId(),"USER");
        return user.getId();
    }

    @Override
    public Boolean validateDuplicate(String email) {
        return userRepository.existsEmail(email);
    }

    @Override
    public User get(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFound::new);
        if (passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        else{
            throw new InvalidPassword();
        }
    }

    @Override
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFound::new);
    }

    @Override
    public Long edit(Long userId,UserEdit userEdit) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        user.edit(userEdit);
        return userRepository.update(user);
    }

    @Override
    public Long editUserStatus(Long id, UserStatus userStatus) {
        User user = userRepository.findById(id).orElseThrow(UserNotFound::new);
        user.editStatus(userStatus.toString());
        userRepository.updateUserStatus(user);
        return user.getId();
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userId);
    }
}
