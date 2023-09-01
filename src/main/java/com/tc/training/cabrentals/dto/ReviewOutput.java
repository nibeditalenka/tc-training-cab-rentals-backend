package com.tc.training.cabrentals.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewOutput {
  private UUID id;
  private String comment;
  private Integer rating;
  private UserOutput user;
  private OrderOutput order;
}
