package com.tc.training.cabrentals.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
@Entity
public class Address extends BaseEntity {
  private String addressLine1;
  private String addressLine2;
  private String landMark;
  private String pinCode;
  private String city;

  @OneToOne( mappedBy = "address" )
  private Center center;

}
