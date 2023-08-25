package com.tc.training.cabrentals.dto;

import java.util.UUID;

import com.tc.training.cabrentals.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutput {
  private UUID id;
  private String name;
  private String email;
  private Role role;
  private String phoneNumber;
}
