package com.tc.training.cabrentals.facade.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.entities.Car;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;
import com.tc.training.cabrentals.facade.OrderFacade;
import com.tc.training.cabrentals.services.CarService;
import com.tc.training.cabrentals.services.OrderService;
import com.tc.training.cabrentals.utils.AppUtils;
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

    order.setOrderStatus( OrderStatus.INITIALIZE );
    Car car = carService.getCarById( input.getCarId() );
    order.setCar( car );
    order.setUser( CurrentUser.get() );
    order.setOrderedDate( LocalDate.now() );

    order = orderService.createOrUpdate( order );
    return modelMapper.map( order, OrderOutput.class );
  }

  @Override
  public PageOutput<OrderOutput> getAllFiltered( final Integer pageNumber, final Integer pageSize, final String sortBy,
      final Sort.Direction sortDirection, final LocalDate orderedDate, final UUID centerId, final UUID userId,
      final UUID carId, final LocalDateTime startDateTime, final LocalDateTime dropDateTime,
      final OrderStatus orderStatus ) {
    Page<Order> orderPage = orderService.getAllFiltered( pageNumber, pageSize, sortBy, sortDirection, orderedDate,
        centerId, userId, carId, startDateTime, dropDateTime, orderStatus );
    return AppUtils.convertPageToPageOutput( orderPage, OrderOutput.class );
  }

  @Override
  public OrderOutput updateOrder( UUID id, OrderInput input ) {
    Order order = modelMapper.map( input, Order.class );
    order.setUser( CurrentUser.get() );
    Order orderById = orderService.getById( id )
        .orElseThrow( () -> new ResourceNotFoundException( "Order is not found in this id" ) );
    return modelMapper.map( orderById, OrderOutput.class );
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

  @Override
  public List<OrderOutput> getByStatus( final OrderStatus status ) {
    List<Order> orders = orderService.getByStatus( status );
    return orders.stream().map( order -> modelMapper.map( order, OrderOutput.class ) ).toList();
  }

  @Override
  public OrderOutput updateStatus( final UUID id, final OrderStatus orderStatus ) {
    Order order = orderService.getById( id )
        .orElseThrow( () -> new ResourceNotFoundException( "Order not found with id " + id ) );
    if( orderStatus.equals( OrderStatus.INITIALIZE ) || orderStatus.equals( OrderStatus.CONFIRMED ) ) {
      throw new ResourceNotFoundException( "Invalid access" );
    }
    order.setOrderStatus( orderStatus );
    return modelMapper.map( orderService.createOrUpdate( order ), OrderOutput.class );
  }
}
