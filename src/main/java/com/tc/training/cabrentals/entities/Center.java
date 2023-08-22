package com.tc.training.cabrentals.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
