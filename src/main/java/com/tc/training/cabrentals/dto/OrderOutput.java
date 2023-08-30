package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderOutput {
  private UUID id;
  private CarOutput car;
  private CenterOutput center;
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
}
