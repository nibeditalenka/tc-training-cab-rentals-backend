package com.tc.training.cabrentals.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.entities.QOrder;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.repositories.OrderRepository;
import com.tc.training.cabrentals.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final static QOrder qOrder = QOrder.order;
  private final OrderRepository orderRepository;

  @Override
  public Order createOrUpdate( Order order ) {
    return orderRepository.save( order );
  }

  @Override
  public Page<Order> getAllFiltered( final Integer pageNumber, final Integer pageSize, final String sortBy,
      final Sort.Direction sortDirection, final LocalDate orderedDate, final UUID centerId, final UUID userId,
      final UUID carId ) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if( orderedDate != null ) {
      booleanBuilder.and( qOrder.orderedDate.eq( orderedDate ) );
    }
    if( userId != null ) {
      booleanBuilder.and( qOrder.user.id.eq( userId ) );
    }
    if( centerId != null ) {
      booleanBuilder.and( qOrder.car.center.id.eq( centerId ) );
    }
    if( carId != null ) {
      booleanBuilder.and( qOrder.car.id.eq( carId ) );
    }

    PageRequest pageRequest = PageRequest.of( pageNumber, pageSize, Sort.by( sortDirection, sortBy ) );
    return orderRepository.findAll( booleanBuilder, pageRequest );
  }

  @Override
  public Optional<Order> getById( UUID id ) {
    return orderRepository.findById( id );
  }

  @Override
  public void deleteById( final UUID id ) {
    orderRepository.deleteById( id );
  }

  @Override
  public Order updateById( final UUID id, final Order order ) {
    return null;
  }

  @Override
  public List<Order> getByStatus( final OrderStatus status ) {
    return orderRepository.findOrdersByOrderStatus( status );
  }

  @Override
  public List<Order> getByCarId( final UUID carId ) {
    return null;
  }

  @Override
  public List<Order> getByCarId( final UUID carId, List<OrderStatus> statuses ) {
    return orderRepository.findByCar_IdAndOrderStatusNotIn( carId, statuses );
  }
}
