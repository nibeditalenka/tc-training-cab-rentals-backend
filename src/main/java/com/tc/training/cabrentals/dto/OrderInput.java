package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderInput {
  private UUID carId;
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
}
