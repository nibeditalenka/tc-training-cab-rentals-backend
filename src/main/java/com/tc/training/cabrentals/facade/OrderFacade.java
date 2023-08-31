package com.tc.training.cabrentals.facade;

import java.util.List;
import java.util.UUID;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.enums.OrderStatus;

public interface OrderFacade {
  OrderOutput placeOrder( OrderInput input );

  List<OrderOutput> getAll();

  OrderOutput updateOrder( UUID id, OrderInput input );

  void deleteOrder( UUID id );

  OrderOutput getById( UUID id );

  List<OrderOutput> getByStatus( OrderStatus status );

  OrderOutput updateStatus( UUID id, OrderStatus orderStatus );
}
