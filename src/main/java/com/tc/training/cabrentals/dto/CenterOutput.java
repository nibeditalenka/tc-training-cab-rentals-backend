package com.tc.training.cabrentals.dto;

import java.util.List;

import com.tc.training.cabrentals.entities.Address;
import com.tc.training.cabrentals.entities.Car;

import lombok.Data;

@Data
public class CenterOutput {
  List<Car> cars;
  private Address address;
  private String name;
}
