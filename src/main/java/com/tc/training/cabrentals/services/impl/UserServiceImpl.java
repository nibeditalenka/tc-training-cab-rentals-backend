package com.tc.training.cabrentals.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
import com.tc.training.cabrentals.repositories.UserRepository;
import com.tc.training.cabrentals.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public User create( User user ) {
    return userRepository.save( user );
  }

  @Override
  public List<User> getAll() {
    return userRepository.findByRole( Role.EMPLOYEE );
  }

  @Override
  public Optional<User> getById( UUID id ) {
    return userRepository.findById( id );
  }

  @Override
  public void deleteById( UUID id ) {
    userRepository.deleteById( id );
  }
}
