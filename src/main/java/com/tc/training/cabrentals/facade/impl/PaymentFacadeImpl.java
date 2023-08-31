package com.tc.training.cabrentals.facade.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.dto.PaymentInput;
import com.tc.training.cabrentals.dto.PaymentOutput;
import com.tc.training.cabrentals.entities.Order;
import com.tc.training.cabrentals.entities.Payment;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;
import com.tc.training.cabrentals.facade.PaymentFacade;
import com.tc.training.cabrentals.services.OrderService;
import com.tc.training.cabrentals.services.PaymentService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade {
  private final PaymentService paymentService;
  private final ModelMapper modelMapper;
  private final OrderService orderService;

  @Override
  public OrderOutput create( final PaymentInput input ) {
    UUID orderId = input.getOrderId();
    Order order = orderService.getById( orderId )
        .orElseThrow( () -> new ResourceNotFoundException( "Order id not found with id " + orderId ) );
    Payment payment = modelMapper.map( input, Payment.class );
    payment = paymentService.createOrUpdate( payment );
    order.setPayment( payment );
    if( payment.getStatus().equals( "Success" ) ) {
      order.setOrderStatus( OrderStatus.CONFIRMED );
    }
    order = orderService.createOrUpdate( order );
    return modelMapper.map( order, OrderOutput.class );
  }

  @Override
  public PaymentOutput getById( final UUID id ) {
    Payment payment = paymentService.getById( id )
        .orElseThrow( () -> new ResourceNotFoundException( "Payment not found with id" ) );
    return modelMapper.map( payment, PaymentOutput.class );
  }

  @Override
  public void delete( final UUID id ) {
    paymentService.deleteById( id );
  }

}
