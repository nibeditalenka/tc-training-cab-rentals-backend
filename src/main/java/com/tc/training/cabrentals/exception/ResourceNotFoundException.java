package com.tc.training.cabrentals.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException( String message ) {
    super( message );
    log.error( message );
  }
}
