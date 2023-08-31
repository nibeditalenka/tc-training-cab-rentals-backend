package com.tc.training.cabrentals.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.enums.OrderStatus;

public interface OrderService {
  Order createOrUpdate( Order order );

  List<Order> getAll();

  Optional<Order> getById( UUID id );

  void deleteById( UUID id );

  Order updateById( UUID id, Order order );

  List<Order> getByStatus( OrderStatus status );
}
