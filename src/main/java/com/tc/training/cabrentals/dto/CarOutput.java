package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class CarOutput {
  private String type;
  private String brand;
  private String name;
  private String seater;
  private String mileage;
  private Float price;
  private Boolean automatic;
  private String image;
}
