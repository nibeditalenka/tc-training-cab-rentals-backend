package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderOutput {
  private String id;
  private String city;
  private String carName;
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
}
