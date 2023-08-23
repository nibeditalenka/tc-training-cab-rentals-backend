package com.tc.training.cabrentals.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, UUID> {
}
