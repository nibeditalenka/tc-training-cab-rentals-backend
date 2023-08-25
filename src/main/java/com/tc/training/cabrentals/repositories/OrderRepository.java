package com.tc.training.cabrentals.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
}
