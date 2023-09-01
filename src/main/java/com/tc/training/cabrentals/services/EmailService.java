package com.tc.training.cabrentals.services;

public interface EmailService {
  void sendEmail( String to, String subject, String body );
}
