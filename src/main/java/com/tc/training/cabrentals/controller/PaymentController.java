package com.tc.training.cabrentals.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.dto.PaymentInput;
import com.tc.training.cabrentals.dto.PaymentOutput;
import com.tc.training.cabrentals.facade.PaymentFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/payments" )
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentFacade paymentFacade;

  @PostMapping
  public OrderOutput create( @RequestBody PaymentInput input ) {
    return paymentFacade.create( input );
  }

  @GetMapping( "/{id}" )
  public PaymentOutput getById( @PathVariable UUID id ) {
    return paymentFacade.getById( id );
  }

  @DeleteMapping( "/{id}" )
  public void deleteById( @PathVariable UUID id ) {
    paymentFacade.delete( id );
  }
}
