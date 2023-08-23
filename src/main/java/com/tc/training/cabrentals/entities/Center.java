package com.tc.training.cabrentals.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
@Entity
@Table( name = "centers" )
public class Center extends BaseEntity {
  private String name;
  @OneToOne( cascade = CascadeType.ALL )
  private Address address;
  @OneToMany
  private List<Car> cars;
  private Boolean isActive = Boolean.TRUE;
}
