package com.tc.training.cabrentals.facade.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.CarOutput;
import com.tc.training.cabrentals.dto.CenterOutput;
import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;
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
    order.setOrderStatus( OrderStatus.PENDING );
    Car car = carService.getCarById( input.getCarId() );
    order.setCar( car );

    order.setUser( CurrentUser.get() );
    order = orderService.create( order );
    OrderOutput orderOutput = modelMapper.map( order, OrderOutput.class );
    orderOutput.setCenter( modelMapper.map( order.getCar().getCenter(), CenterOutput.class ) );
    orderOutput.setCar( modelMapper.map( order.getCar(), CarOutput.class ) );
    return orderOutput;
  }

  @Override
  public List<OrderOutput> getAll() {
    return null;
  }

  @Override
  public OrderOutput updateOrder( UUID id, OrderInput input ) {
    return null;
  }

  @Override
  public void deleteOrder( UUID id ) {
    orderService.deleteById( id );
  }

  @Override
  public OrderOutput getById( UUID id ) {
    Order order = orderService.getById( id )
        .orElseThrow( () -> new ResourceNotFoundException( "Order is not found with this id" ) );
    return modelMapper.map( order, OrderOutput.class );
  }
}
