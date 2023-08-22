package com.tc.training.cabrentals.controller;

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

  public OrderOutput placeOrder( OrderInput input ) {
    return orderFacade.placeOrder( input );
  }
}
