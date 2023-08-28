package com.tc.training.cabrentals.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.facade.OrderFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/orders" )
@RequiredArgsConstructor
public class OrderController {
  private final OrderFacade orderFacade;

  @PostMapping
  public OrderOutput placeOrder( OrderInput input ) {
    return orderFacade.placeOrder( input );
  }

  @GetMapping
  public List<OrderOutput> getAll() {
    return null;
  }

  @GetMapping( "/{id}" )
  public OrderOutput getOrderById( @PathVariable UUID id ) {
    return null;
  }

  @DeleteMapping
  public void deleteOrderById( @RequestParam UUID id ) {

  }

}
