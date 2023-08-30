package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {
  private UUID carId;
  private LocalDateTime pickUpDate;
  private LocalDateTime returnDate;
}
