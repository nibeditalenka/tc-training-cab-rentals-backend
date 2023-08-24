package com.tc.training.cabrentals.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;

public interface UserService {
  User createOrUpdate( User user );

  Page<User> getAllByFilters( final Integer pageSize, final Integer pageNumber, final Role role );

  Optional<User> getById( UUID id );

  void deleteById( UUID id );

  User add( User user );

  Boolean userExistsByRole( Role role );
}
