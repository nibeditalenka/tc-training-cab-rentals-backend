package com.tc.training.cabrentals.repositories;

import com.tc.training.cabrentals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
