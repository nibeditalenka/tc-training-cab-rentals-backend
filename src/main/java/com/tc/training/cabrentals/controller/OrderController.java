package com.tc.training.cabrentals.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
