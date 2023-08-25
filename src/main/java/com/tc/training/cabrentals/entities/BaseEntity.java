package com.tc.training.cabrentals.entities;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.Version;

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
  @EqualsAndHashCode.Include
  protected String id = UUID.randomUUID().toString();

  @Version
  protected String version;
}
