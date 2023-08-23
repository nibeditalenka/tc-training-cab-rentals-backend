package com.tc.training.cabrentals.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Address extends BaseEntity {
  private String addressLine1;
  private String addressLine2;
  private String landMark;
  private String pinCode;
  private String city;

}
