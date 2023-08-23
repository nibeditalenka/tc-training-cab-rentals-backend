package com.tc.training.cabrentals.entities;

import com.tc.training.cabrentals.enums.CarStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Car extends BaseEntity {
  private CarStatus carStatus;
  private String plateNumber;
  private String type;
  private String brand;
  private String name;
  private String seater;
  private String mileage;
  private Float price;
  private Boolean automatic;
  private Integer tripCount;
  private Float averageRatings;
  private String image;
  @ManyToOne
  @JoinColumn( name = "order_id" ) // This assumes you have a column named "order_id" in the cars table
  private Order order;
  @ManyToOne
  private Center center;
}
