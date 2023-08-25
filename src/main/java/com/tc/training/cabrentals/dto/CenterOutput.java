package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class CenterOutput {
  private String id;
  private String name;
  private AddressOutput address;
}
