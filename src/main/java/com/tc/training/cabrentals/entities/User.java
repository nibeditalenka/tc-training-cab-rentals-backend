package com.tc.training.cabrentals.entities;

import com.tc.training.cabrentals.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name = "users" )
public class User extends BaseEntity {
  private String name;
  private String email;
  private String phoneNum;
  private String firebaseId;

  @Enumerated( EnumType.STRING )
  private Role role;
}
