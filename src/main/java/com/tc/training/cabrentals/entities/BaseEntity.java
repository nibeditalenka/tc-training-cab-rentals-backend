package com.tc.training.cabrentals.entities;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
  @GeneratedValue( strategy = GenerationType.AUTO )
  @EqualsAndHashCode.Include
  protected UUID id;

  protected String version;
}
