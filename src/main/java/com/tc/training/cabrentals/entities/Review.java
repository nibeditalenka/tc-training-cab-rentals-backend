package com.tc.training.cabrentals.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode( callSuper = true )
public class Review extends BaseEntity {
  private String comment;
  private Integer rating;
  @ManyToOne
  private User user;
  @OneToOne
  private Order order;
}
