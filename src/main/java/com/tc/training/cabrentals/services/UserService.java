package com.tc.training.cabrentals.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tc.training.cabrentals.entities.User;

public interface UserService {
  User create( User user );

  List<User> getAll();

  Optional<User> getById( UUID id );

  void deleteById( UUID id );

  User add( User user );
}
