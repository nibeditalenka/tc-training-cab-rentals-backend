package com.tc.training.cabrentals.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewInput {
  private String comment;
  private Integer rating;
  private UUID orderId;
}
