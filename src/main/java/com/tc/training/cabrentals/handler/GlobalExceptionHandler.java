package com.tc.training.cabrentals.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tc.training.cabrentals.dto.ErrorDetails;
import com.tc.training.cabrentals.exception.InvalidAccessException;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GlobalExceptionHandler {
  @ExceptionHandler( ResourceNotFoundException.class )
  @ResponseStatus( HttpStatus.NOT_FOUND )
  public ErrorDetails HandleResourceNotFoundException( final ResourceNotFoundException exception ) {
    return new ErrorDetails( exception.getMessage() );
  }

  @ExceptionHandler( InvalidAccessException.class )
  @ResponseStatus( HttpStatus.NOT_ACCEPTABLE )
  public ErrorDetails HandleInvalidAccessException( final InvalidAccessException exception ) {
    return new ErrorDetails( exception.getMessage() );
  }

  @ExceptionHandler( RuntimeException.class )
  @ResponseStatus( HttpStatus.BAD_REQUEST )
  public ErrorDetails handleException( final RuntimeException exception ) {
    return new ErrorDetails( exception.getMessage() );
  }

}
