package com.tc.training.cabrentals.entities;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue( strategy = GenerationType.UUID )
  protected UUID id;

  protected String version;
}
