package com.tc.training.cabrentals.dto;

import java.util.UUID;

import com.tc.training.cabrentals.enums.Gear;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarInput {
  private String type;
  private String plateNumber;
  private String brand;
  private String model;
  private String seater;
  private String mileage;
  private Float price;
  private String image;
  private UUID centerId;
  private Gear gear;
}
