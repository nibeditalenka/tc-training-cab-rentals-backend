package com.tc.training.cabrentals;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tc.training.cabrentals.dto.ErrorDetails;
import com.tc.training.cabrentals.exception.BusinessException;
import com.tc.training.cabrentals.exception.InvalidAccessException;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
  @ExceptionHandler( ResourceNotFoundException.class )
  @ResponseStatus( HttpStatus.NOT_FOUND )
  public ErrorDetails HandleResourceNotFoundException( final ResourceNotFoundException exception ) {
    return errorBuilder( exception );
  }

  @ExceptionHandler( BusinessException.class )
  @ResponseStatus( HttpStatus.NOT_FOUND )
  public ErrorDetails handleBusinessException( final BusinessException exception ) {
    return errorBuilder( exception );
  }

  @ExceptionHandler( InvalidAccessException.class )
  @ResponseStatus( HttpStatus.NOT_ACCEPTABLE )
  public ErrorDetails HandleInvalidAccessException( final InvalidAccessException exception ) {
    return errorBuilder( exception );
  }

  @ExceptionHandler( Exception.class )
  @ResponseStatus( HttpStatus.BAD_REQUEST )
  public ErrorDetails handleException( final Exception exception ) {
    return errorBuilder( exception );
  }

  @ExceptionHandler( RuntimeException.class )
  @ResponseStatus( HttpStatus.BAD_REQUEST )
  public ErrorDetails handleRunTime( final RuntimeException exception ) {
    return errorBuilder( exception );
  }

  public ErrorDetails errorBuilder( final Exception exception ) {
    log.error( "{} - {}", exception.getClass().getSimpleName(), exception.getMessage() );
    return new ErrorDetails( exception.getMessage() );
  }
}
