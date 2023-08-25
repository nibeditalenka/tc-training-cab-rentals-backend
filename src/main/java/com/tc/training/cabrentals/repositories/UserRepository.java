package com.tc.training.cabrentals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {
  Boolean existsByRole( Role role );
}
