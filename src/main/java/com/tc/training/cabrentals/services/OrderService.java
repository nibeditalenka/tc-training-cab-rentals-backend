package com.tc.training.cabrentals.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tc.training.cabrentals.entities.Order;

public interface OrderService {
  Order create( Order order );

  List<Order> getAll();

  Optional<Order> getById( UUID id );

  void deleteById( UUID id );
}
