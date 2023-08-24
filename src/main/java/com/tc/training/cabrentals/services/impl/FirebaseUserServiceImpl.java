package com.tc.training.cabrentals.services.impl;

import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.exception.BusinessException;
import com.tc.training.cabrentals.services.FirebaseUserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FirebaseUserServiceImpl implements FirebaseUserService {

  @Override
  public UserRecord createUser( UserInput input ) {
    UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
    createRequest.setPassword( input.getPassword() );
    createRequest.setDisplayName( input.getName() );
    createRequest.setEmail( input.getEmail() );
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    try {
      return firebaseAuth.createUser( createRequest );
    } catch( FirebaseAuthException e ) {
      throw new RuntimeException( e );
    }
  }

  @Override
  public String getVerificationLink( String email ) {
    try {
      return FirebaseAuth.getInstance().generateEmailVerificationLink( email );
    } catch( FirebaseAuthException e ) {
      throw new RuntimeException( e );
    }

  }

  public UserRecord getByEmail( String email ) {
    try {
      return FirebaseAuth.getInstance().getUserByEmail( email );
    } catch( FirebaseAuthException e ) {
      throw new BusinessException( e.getMessage() );
    }
  }

}
