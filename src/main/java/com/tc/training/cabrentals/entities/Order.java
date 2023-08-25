package com.tc.training.cabrentals.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
  @OneToOne
  private Car car;
}
