package com.tc.training.cabrentals.facade.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import com.tc.training.cabrentals.dto.FirebaseTokenOutput;
import com.tc.training.cabrentals.dto.LogInOutput;
import com.tc.training.cabrentals.dto.LoginInput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
import com.tc.training.cabrentals.exception.ResourceNotFoundException;
import com.tc.training.cabrentals.facade.UserFacade;
import com.tc.training.cabrentals.services.CenterService;
import com.tc.training.cabrentals.services.FirebaseUserService;
import com.tc.training.cabrentals.services.UserService;
import com.tc.training.cabrentals.utils.AppUtils;
import com.tc.training.cabrentals.utils.CurrentUser;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacadeIml implements UserFacade {
  private final UserService userService;
  private final ModelMapper modelMapper;
  private final FirebaseUserService firebaseUserService;
  private final CenterService centerService;

  @Value( "${firebase.api-key}" )
  private String firebaseApiKey;
  @Value( "${firebase.accounts-url}" )
  private String firebaseUrl;

  @Override
  public UserOutput createEmployee( UserInput input ) {
    Center center = centerService.getById( input.getCenterId() );
    User user = modelMapper.map( input, User.class );
    user.setRole( Role.EMPLOYEE );
    UserRecord firebaseUser = firebaseUserService.createUser( input );
    user.setFirebaseId( firebaseUser.getUid() );
    user.setCenter( center );
    user = userService.createOrUpdate( user );
    return modelMapper.map( user, UserOutput.class );
  }

  @Override
  public PageOutput<UserOutput> getAllEmployee( final Integer pageSize, final Integer pageNumber, final Role role ) {
    Page<User> users = userService.getAllByFilters( pageSize, pageNumber, role );
    return AppUtils.convertPageToPageOutput( users, UserOutput.class );
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
    User add = userService.createOrUpdate( user );
    String verificationLink = firebaseUserService.getVerificationLink( add.getEmail() );
    return modelMapper.map( add, UserOutput.class );
  }

  @Override
  public LogInOutput doLogin( LoginInput input ) {
    String url = firebaseUrl + ":signInWithPassword?key=" + firebaseApiKey;
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put( "email", input.getEmail() );
    requestBody.put( "password", input.getPassword() );
    requestBody.put( "returnSecureToken", true );
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    HttpEntity<?> httpEntity = new HttpEntity<>( requestBody, httpHeaders );
    ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, httpEntity, String.class );
    String body = response.getBody();
    Gson gson = new Gson();
    FirebaseTokenOutput firebaseTokenOutput = gson.fromJson( body, FirebaseTokenOutput.class );
    LogInOutput logInOutput = new LogInOutput();
    logInOutput.setExpiresIn( firebaseTokenOutput.getExpiresIn() );
    logInOutput.setAccessToken( firebaseTokenOutput.getIdToken() );
    logInOutput.setRefreshToken( firebaseTokenOutput.getRefreshToken() );
    User user = userService.getByFirebaseId( firebaseTokenOutput.getLocalId() );
    logInOutput.setUser( modelMapper.map( user, UserOutput.class ) );
    return logInOutput;
  }

  @Override
  public UserOutput getMe() {
    return modelMapper.map( CurrentUser.get(), UserOutput.class );
  }

}
