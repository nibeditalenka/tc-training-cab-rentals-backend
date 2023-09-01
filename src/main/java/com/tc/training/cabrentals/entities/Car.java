package com.tc.training.cabrentals.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tc.training.cabrentals.enums.CarStatus;
import com.tc.training.cabrentals.enums.FuelType;
import com.tc.training.cabrentals.enums.Gear;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
@Entity
public class Car extends BaseEntity {
  private CarStatus carStatus;
  private String plateNumber;
  private String type;
  private String brand;
  private String model;
  private String seater;
  private String mileage;
  private Float price;
  private Integer tripCount;
  private Double averageRatings = 0.0;
  private Long numberOfRatings = 0L;
  private String image;
  private Gear gear;
  private FuelType fuelType;
  @OneToMany
  //@JoinColumn( name = "order_id" ) // This assumes you have a column named "order_id" in the cars table
  private List<Order> order;
  @ManyToOne
  private Center center;
}
