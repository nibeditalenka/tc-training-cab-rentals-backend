package com.tc.training.cabrentals.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tc.training.cabrentals.enums.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table( name = "users" )
public class User extends BaseEntity {
  private String name;
  private String email;
  private String phoneNumber;
  private String firebaseId;

  @ManyToOne
  private Center center;

  @Enumerated( EnumType.STRING )
  private Role role;
}
