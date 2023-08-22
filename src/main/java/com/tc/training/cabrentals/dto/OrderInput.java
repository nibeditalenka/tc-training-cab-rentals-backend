package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderInput {
  private String city;
  private String centerName;
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
}
