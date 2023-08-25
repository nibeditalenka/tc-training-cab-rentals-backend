package com.tc.training.cabrentals.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CenterTransferDto {
  private Boolean isTransferringCars;
  private UUID centerId;
}
