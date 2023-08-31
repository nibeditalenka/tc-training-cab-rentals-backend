package com.tc.training.cabrentals.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.checkerframework.checker.index.qual.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {
  @NotNull
  private UUID carId;

  @NotNull
  private LocalDateTime pickUpDate;

  @NotNull
  private LocalDateTime returnDate;

  @NotNull
  @Positive
  private Float price;

  @NotNull
  @Positive
  private Float gst;

  @NotNull
  @Positive
  private Float totalPrice;
}
