package com.tc.training.cabrentals.facade.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.UserRecord;
import com.tc.training.cabrentals.dto.LoginInput;
import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;
import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;
import com.tc.training.cabrentals.facade.UserFacade;
import com.tc.training.cabrentals.services.FirebaseUserService;
import com.tc.training.cabrentals.services.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacadeIml implements UserFacade {
  private final UserService userService;
  private final ModelMapper modelMapper;
  private final FirebaseUserService firebaseUserService;

  @Override
  public UserOutput createEmployee( UserInput input ) {
    User user = modelMapper.map( input, User.class );
    user.setRole( Role.EMPLOYEE );
    UserRecord firebaseUser = firebaseUserService.createUser( input );
    user.setFirebaseId( firebaseUser.getUid() );
    user = userService.createOrUpdate( user );
    return modelMapper.map( user, UserOutput.class );
  }

  @Override
  public List<UserOutput> getAllEmployee() {
    List<User> users = userService.getAll();
    return users.stream().map( user -> modelMapper.map( user, UserOutput.class ) ).toList();
  }

  @Override
  public UserOutput getEmployeeById( UUID id ) {
    User user = userService.getById( id )
        .orElseThrow( () -> new ResourceNotFoundException( "User not found with this id" ) );
    return modelMapper.map( user, UserOutput.class );
  }

  @Override
  public void deleteEmployeeById( UUID id ) {
    userService.deleteById( id );
  }

  @Override
  public UserOutput doSignup( UserInput input ) {
    User user = modelMapper.map( input, User.class );
    user.setRole( Role.END_USER );
    UserRecord userRecord = firebaseUserService.createUser( input );
    user.setFirebaseId( userRecord.getUid() );
    User add = userService.add( user );
    String verificationLink = firebaseUserService.getVerificationLink( add.getEmail() );
    return modelMapper.map( add, UserOutput.class );
  }

  @Override
  public UserOutput doLogin( final LoginInput input ) {
    return null;
  }

}
