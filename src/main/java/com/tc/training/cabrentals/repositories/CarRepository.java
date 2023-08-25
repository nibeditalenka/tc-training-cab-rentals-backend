package com.tc.training.cabrentals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String>, QuerydslPredicateExecutor<Car> {
}
