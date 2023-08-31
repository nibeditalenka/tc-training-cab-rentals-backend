package com.tc.training.cabrentals.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseEntity {
  private Float amount;
  private String referenceId;
  private String status;
  private String additionalDetails;
}
