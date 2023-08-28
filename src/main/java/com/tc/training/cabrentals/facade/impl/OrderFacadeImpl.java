package com.tc.training.cabrentals.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.facade.OrderFacade;
import com.tc.training.cabrentals.services.CarService;
import com.tc.training.cabrentals.services.OrderService;
import com.tc.training.cabrentals.utils.CurrentUser;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
  private final OrderService orderService;
  private final ModelMapper modelMapper;
  private final CarService carService;

  @Override
  public OrderOutput placeOrder( OrderInput input ) {
    Order order = modelMapper.map( input, Order.class );
    order.setUser( CurrentUser.get() );
    order.setOrderStatus( OrderStatus.PENDING );
    Car car = carService.getCarById( input.getCarId() );
    order.setCar( car );
    orderService.create( order );
    OrderOutput orderOutput = modelMapper.map( order, OrderOutput.class );
    orderOutput.setCity( car.getCenter().getAddress().getCity() );
    orderOutput.setCarName( car.getModel() );
    return orderOutput;
  }
}
