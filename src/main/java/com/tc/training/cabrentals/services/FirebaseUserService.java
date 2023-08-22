package com.tc.training.cabrentals.services;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.UserRecord;
import com.tc.training.cabrentals.dto.UserInput;

public interface FirebaseUserService {
  UserRecord createUser( UserInput input );

  Iterable<ExportedUserRecord> getAll();

  String getVerificationLink( String email );
}
