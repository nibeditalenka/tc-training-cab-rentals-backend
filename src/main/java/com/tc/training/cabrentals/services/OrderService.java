package com.tc.training.cabrentals.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.enums.OrderStatus;

public interface OrderService {
  Order createOrUpdate( Order order );

  Page<Order> getAllFiltered( final Integer pageNumber, final Integer pageSize, final String sortBy,
      final Sort.Direction sortDirection, final LocalDate orderedDate, final UUID centerId, final UUID userId,
      final UUID carId, final LocalDateTime startDateTime, final LocalDateTime dropDateTime );

  Optional<Order> getById( UUID id );

  void deleteById( UUID id );

  Order updateById( UUID id, Order order );

  List<Order> getByStatus( OrderStatus status );

  List<Order> getByCarId( UUID carId );

  List<Order> getByCarId( UUID carId, List<OrderStatus> statuses );
}
