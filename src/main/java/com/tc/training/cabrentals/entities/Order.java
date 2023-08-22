package com.tc.training.cabrentals.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.tc.training.cabrentals.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table( name = "orders" )
public class Order extends BaseEntity {
  private LocalDateTime pickUpDate;
  private LocalDateTime returnDate;
  private OrderStatus orderStatus;
  @ManyToOne( cascade = CascadeType.ALL )
  private User user;
  @OneToMany( mappedBy = "order" )
  private List<Car> car;
}
