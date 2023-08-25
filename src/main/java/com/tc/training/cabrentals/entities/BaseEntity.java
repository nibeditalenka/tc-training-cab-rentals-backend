package com.tc.training.cabrentals.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

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
  @GeneratedValue( generator = "UUID" )
  @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
  @Column( name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false )
  @EqualsAndHashCode.Include
  protected UUID id;

  protected String version;
}
