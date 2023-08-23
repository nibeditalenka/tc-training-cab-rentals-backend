package com.tc.training.cabrentals.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
  List<User> findByRole( Role role );
}
