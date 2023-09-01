package com.tc.training.cabrentals.facade;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.enums.OrderStatus;

public interface OrderFacade {
  OrderOutput placeOrder( OrderInput input );

  PageOutput<OrderOutput> getAllFiltered( final Integer pageNumber, final Integer pageSize, final String sortBy,
      final Sort.Direction sortDirection, final LocalDate orderedDate, final UUID centerId, final UUID userId,
      final UUID carId );

  OrderOutput updateOrder( UUID id, OrderInput input );

  void deleteOrder( UUID id );

  OrderOutput getById( UUID id );

  List<OrderOutput> getByStatus( OrderStatus status );

  OrderOutput updateStatus( UUID id, OrderStatus orderStatus );
}
