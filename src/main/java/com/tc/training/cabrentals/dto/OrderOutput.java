package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderOutput {
  private UUID id;
  private Float price;
  private CarOutput car;
  private LocalDateTime pickUpOrder;
  private LocalDateTime returnDate;
  private Float gst;
  private Float totalPrice;
  private PaymentOutput payment;
}
