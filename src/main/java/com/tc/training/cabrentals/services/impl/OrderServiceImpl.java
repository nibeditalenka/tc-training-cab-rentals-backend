package com.tc.training.cabrentals.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.repositories.OrderRepository;
import com.tc.training.cabrentals.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  @Override
  public Order create( Order order ) {
    return orderRepository.save( order );
  }

  @Override
  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  @Override
  public Optional<Order> getById( UUID id ) {
    return orderRepository.findById( id );
  }

  @Override
  public void deleteById( final UUID id ) {
    orderRepository.deleteById( id );
  }
}
