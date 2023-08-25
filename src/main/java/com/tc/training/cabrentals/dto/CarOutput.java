package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class CarOutput {
  private String id;
  private String type;
  private String brand;
  private String model;
  private String seater;
  private String mileage;
  private Float price;
  private Boolean automatic;
  private String image;
  private String centerId;
  private String plateNumber;
}
