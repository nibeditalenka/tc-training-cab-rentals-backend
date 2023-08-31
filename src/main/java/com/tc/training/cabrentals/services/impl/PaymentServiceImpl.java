package com.tc.training.cabrentals.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tc.training.cabrentals.entities.Payment;
import com.tc.training.cabrentals.repositories.PaymentRepository;
import com.tc.training.cabrentals.services.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository paymentRepository;

  @Override
  public Payment createOrUpdate( final Payment payment ) {
    return paymentRepository.save( payment );
  }

  @Override
  public Optional<Payment> getById( final UUID id ) {
    return paymentRepository.findById( id );
  }

  @Override
  public void deleteById( final UUID id ) {
    paymentRepository.deleteById( id );
  }
}
