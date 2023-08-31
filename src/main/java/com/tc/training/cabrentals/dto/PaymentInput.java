package com.tc.training.cabrentals.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInput {
  private UUID orderId;
  private Float amount;
  private String referenceId;
  private String status;
  private String additionalDetails;
}
