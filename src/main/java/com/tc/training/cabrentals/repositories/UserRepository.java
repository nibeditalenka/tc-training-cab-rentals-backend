package com.tc.training.cabrentals.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, QuerydslPredicateExecutor<User> {
  Boolean existsByRole( Role role );

  User findByFirebaseId( String id );
}
