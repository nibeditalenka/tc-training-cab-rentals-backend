package com.tc.training.cabrentals.dto;

import lombok.Data;

@Data
public class LoginOutput {
  private String accessToken;
  private String refreshToken;
  private String expiresIn;
  private UserOutput user;
}
