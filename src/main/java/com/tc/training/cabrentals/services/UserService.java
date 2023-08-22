package com.tc.training.cabrentals.services;

import com.tc.training.cabrentals.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User create(User user);

    List<User> getAll();
    Optional<User> getById(UUID id);
    void deleteById(UUID id);
}
