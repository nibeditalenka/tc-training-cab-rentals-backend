package com.tc.training.cabrentals.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.facade.OrderFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/orders" )
@RequiredArgsConstructor
public class OrderController {
  private final OrderFacade orderFacade;

  @PostMapping
  public OrderOutput placeOrder( @RequestBody OrderInput input ) {
    return orderFacade.placeOrder( input );
  }

  @GetMapping
  public List<OrderOutput> getAll() {
    return orderFacade.getAll();
  }

  @GetMapping( "/{id}" )
  public OrderOutput getOrderById( @PathVariable UUID id ) {
    return orderFacade.getById( id );
  }

  @PutMapping( "/{id}" )
  public OrderOutput updateOrderById( @PathVariable UUID id, @RequestBody OrderInput input ) {
    return orderFacade.updateOrder( id, input );
  }

  @DeleteMapping( "/{id}" )
  public void deleteOrderById( @PathVariable UUID id ) {
    orderFacade.deleteOrder( id );
  }

  @GetMapping( "/order_status" )
  public List<OrderOutput> getByStatus( @RequestParam OrderStatus status ) {
    return orderFacade.getByStatus( status );
  }

}
