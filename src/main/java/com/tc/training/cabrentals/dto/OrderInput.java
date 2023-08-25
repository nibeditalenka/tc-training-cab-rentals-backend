package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {
  private String carId;
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
}
