package com.tc.training.cabrentals.services;

import com.google.firebase.auth.UserRecord;
import com.tc.training.cabrentals.dto.UserInput;

public interface FirebaseUserService {
  UserRecord createUser( UserInput input );

  String getVerificationLink( String email );

  UserRecord getByEmail( String email );
}
