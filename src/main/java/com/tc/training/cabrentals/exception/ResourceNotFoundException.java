package com.tc.training.cabrentals.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException( String message ) {
    super( message );
  }
}
