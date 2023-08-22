package com.tc.training.cabrentals.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvalidAccessException extends RuntimeException {
  public InvalidAccessException( String message ) {
    super( message );
    log.error( message );
  }
}
