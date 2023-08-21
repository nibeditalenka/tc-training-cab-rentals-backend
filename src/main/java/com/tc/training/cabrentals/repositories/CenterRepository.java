package com.tc.training.cabrentals.repositories;

import com.tc.training.cabrentals.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CenterRepository extends JpaRepository<Center, UUID> {

}
