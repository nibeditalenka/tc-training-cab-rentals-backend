package com.tc.training.cabrentals.services.impl;

import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.services.FirebaseUserService;

@Service
public class FirebaseUserServiceImpl implements FirebaseUserService {

  @Override
  public String createUser( UserInput input ) {
    UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
    createRequest.setPhoneNumber( input.getPhoneNum() );
    createRequest.setPassword( input.getPassword() );
    createRequest.setDisplayName( input.getName() );
    createRequest.setEmail( input.getEmail() );
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    try {
      UserRecord user = firebaseAuth.createUser( createRequest );
      String verificationLink = firebaseAuth.generateEmailVerificationLink( user.getEmail() );
      return user.getUid();
    } catch( FirebaseAuthException e ) {
      throw new RuntimeException( e );
    }
  }
}
