package com.tc.training.cabrentals.dto;

import java.util.UUID;

import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.enums.FuelType;
import com.tc.training.cabrentals.enums.Gear;

import lombok.Data;

@Data
public class CarOutput {
  private UUID id;
  private String type;
  private String brand;
  private String model;
  private String seater;
  private String mileage;
  private Float price;
  private String image;
  private UUID centerId;
  private String plateNumber;
  private Gear gear;
  private FuelType fuelType;
  private CarStatus carStatus;
  private Float averageRatings;
}
