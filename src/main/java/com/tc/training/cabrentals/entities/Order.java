package com.tc.training.cabrentals.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tc.training.cabrentals.enums.OrderStatus;

import lombok.Data;

@Entity
@Data
@Table( name = "orders" )
public class Order extends BaseEntity {
  private LocalDateTime pickUpDate;
  private LocalDateTime returnDate;
  private OrderStatus orderStatus;
  @ManyToOne
  private User user;
  @OneToMany
  private List<Car> car;
}
