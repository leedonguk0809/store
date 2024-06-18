package com.example.store.service.user;

import com.example.store.domain.User;
import com.example.store.repository.UserRepository;
import com.example.store.request.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Long signup(UserCreate userCreate) {
        User user = User.from(userCreate);
        userRepository.save(user);
        userRepository.saveUserRole(user.getId(),"USER");
        return user.getId();
    }

    @Override
    public Boolean validateDuplicate(String email) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }
}
