package com.tc.training.cabrentals.facade;

import java.util.UUID;

import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.dto.PaymentInput;
import com.tc.training.cabrentals.dto.PaymentOutput;

public interface PaymentFacade {
  OrderOutput create( PaymentInput input );

  PaymentOutput getById( UUID id );

  void delete( UUID id );
}
