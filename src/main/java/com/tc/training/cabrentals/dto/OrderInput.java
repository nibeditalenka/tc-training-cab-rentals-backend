package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tc.training.cabrentals.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {
  private UUID carId;
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
  private OrderStatus status;
}
