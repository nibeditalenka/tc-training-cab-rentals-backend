package com.tc.training.cabrentals.facade.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.facade.OrderFacade;
import com.tc.training.cabrentals.services.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
  private final OrderService orderService;
  private final ModelMapper modelMapper;

  @Override
  public OrderOutput placeOrder( final OrderInput input ) {
    Order order = modelMapper.map( input, Order.class );
    orderService.create( order );
    return modelMapper.map( order, OrderOutput.class );
  }

  @Override
  public List<OrderOutput> getAll() {
    List<Order> orders = orderService.getAll();
    List<OrderOutput> orderOutputs = orders.stream().map( order -> modelMapper.map( order, OrderOutput.class ) )
        .toList();
    return orderOutputs;
  }
}
