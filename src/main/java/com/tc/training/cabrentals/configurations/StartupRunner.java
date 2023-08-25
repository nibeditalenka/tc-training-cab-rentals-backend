package com.tc.training.cabrentals.configurations;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.google.firebase.auth.UserRecord;
import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
import com.tc.training.cabrentals.services.FirebaseUserService;
import com.tc.training.cabrentals.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class StartupRunner implements ApplicationRunner {
  private final UserService userService;
  private final FirebaseUserService firebaseUserService;

  @Override
  public void run( final ApplicationArguments args ) {
    if( !userService.userExistsByRole( Role.ADMIN ) ) {
      log.info( "Creating Admin user" );
      User user = new User();
      user.setName( "Admin" );
      user.setRole( Role.ADMIN );
      user.setEmail( "admin@cr.com" );

      try {
        UserRecord userRecord = firebaseUserService.getByEmail( user.getEmail() );
        user.setFirebaseId( userRecord.getUid() );
      } catch( Exception e ) {
        UserInput userInput = new UserInput();
        userInput.setEmail( user.getEmail() );
        userInput.setName( user.getName() );
        userInput.setPassword( "password" );

        UserRecord userRecord = firebaseUserService.createUser( userInput );

        user.setFirebaseId( userRecord.getUid() );
      }

      user = userService.createOrUpdate( user );
      log.info( "Admin user created {}", user );
    }
  }
}
