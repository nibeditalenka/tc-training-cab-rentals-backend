package com.tc.training.cabrentals.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

}
