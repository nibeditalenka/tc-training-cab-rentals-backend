package com.tc.training.cabrentals.facade.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.UserRecord;
import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;
import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
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
    User map = modelMapper.map( input, User.class );
    map.setRole( Role.EMPLOYEE );
    UserRecord firebaseUser = firebaseUserService.createUser( input );
    map.setFirebaseId( firebaseUser.getUid() );
    User user = userService.create( map );
    UserOutput output = modelMapper.map( user, UserOutput.class );
    return output;
  }

  @Override
  public List<UserOutput> getAllEmployee() {
    List<User> users = userService.getAll();
    List<UserOutput> userOutputs = users.stream().map( user -> modelMapper.map( user, UserOutput.class ) ).toList();
    return userOutputs;
  }

  @Override
  public UserOutput getEmployeeById( UUID id ) {
    User user = userService.getById( id ).orElseThrow( () -> new RuntimeException( "User not found with this id" ) );
    return modelMapper.map( user, UserOutput.class );
  }

  @Override
  public void deleteEmployeeById( UUID id ) {
    userService.deleteById( id );
  }

  @Override
  public UserOutput doSignup( UserInput input ) {
    User user = modelMapper.map( input, User.class );
    UserRecord userRecord = firebaseUserService.createUser( input );
    user.setFirebaseId( userRecord.getUid() );
    user.setRole( Role.END_USER );
    User add = userService.add( user );
    firebaseUserService.getVerificationLink( add.getEmail() );
    return modelMapper.map( add, UserOutput.class );
  }

}
