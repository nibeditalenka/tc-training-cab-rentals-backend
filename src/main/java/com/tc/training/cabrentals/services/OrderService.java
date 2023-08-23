package com.tc.training.cabrentals.services;

import java.util.List;

import com.tc.training.cabrentals.entities.Order;

public interface OrderService {
  Order create( Order order );

  List<Order> getAll();
}
