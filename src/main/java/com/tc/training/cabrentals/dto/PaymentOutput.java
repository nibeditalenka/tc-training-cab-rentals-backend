package com.tc.training.cabrentals.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentOutput {
  private Float amount;
  private String referenceId;
  private String status;
  private String additionalDetails;
}
