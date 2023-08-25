package com.tc.training.cabrentals.dto;

import java.util.UUID;

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
  private Boolean automatic;
  private String image;
  private UUID centerId;
  private String plateNumber;
}
