package com.tc.training.cabrentals.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.tc.training.cabrentals.enums.OrderStatus;

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
  private LocalDate orderedDate;
  private OrderStatus orderStatus;
}
