package com.tc.training.cabrentals.services;

import java.util.Optional;
import java.util.UUID;

import com.tc.training.cabrentals.entities.Payment;

public interface PaymentService {
  Payment createOrUpdate( Payment payment );

  Optional<Payment> getById( UUID id );

  void deleteById( UUID id );
}
