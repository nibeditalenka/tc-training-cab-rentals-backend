package com.tc.training.cabrentals.entities;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue( strategy = GenerationType.UUID )
  @EqualsAndHashCode.Include
  protected UUID id;

  protected String version;
}
