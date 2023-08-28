package com.tc.training.cabrentals.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInOutput {
  private String accessToken;
  private String refreshToken;
  private String expiresIn;
  private UserOutput user;
}
