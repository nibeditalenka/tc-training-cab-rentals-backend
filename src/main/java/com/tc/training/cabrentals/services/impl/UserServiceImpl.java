package com.tc.training.cabrentals.services.impl;

import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.repositories.UserRepository;
import com.tc.training.cabrentals.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
