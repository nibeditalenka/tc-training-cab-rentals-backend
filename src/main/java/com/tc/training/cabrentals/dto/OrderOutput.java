package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderOutput {
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
}
