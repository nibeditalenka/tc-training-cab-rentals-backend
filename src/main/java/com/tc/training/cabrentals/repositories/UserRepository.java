package com.tc.training.cabrentals.repositories;

import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public List<User> findByRole(Role role);
}
