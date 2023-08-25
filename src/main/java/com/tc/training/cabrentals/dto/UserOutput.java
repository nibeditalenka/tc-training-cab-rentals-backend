package com.tc.training.cabrentals.dto;

import com.tc.training.cabrentals.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutput {
  private String id;
  private String name;
  private String email;
  private Role role;
}
