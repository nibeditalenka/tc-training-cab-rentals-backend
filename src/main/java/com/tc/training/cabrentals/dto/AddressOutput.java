package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class AddressOutput {

  private String id;
  private String addressLine1;
  private String addressLine2;
  private String landMark;
  private String pinCode;
  private String city;
}
