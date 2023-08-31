package com.tc.training.cabrentals.facade;

import java.util.UUID;

import com.tc.training.cabrentals.dto.LogInOutput;
import com.tc.training.cabrentals.dto.LoginInput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;
import com.tc.training.cabrentals.enums.Role;

public interface UserFacade {
  UserOutput createEmployee( UserInput input );

  PageOutput<UserOutput> getAllEmployee( final Integer pageSize, final Integer pageNumber, Role role );

  UserOutput getEmployeeById( UUID id );

  void deleteEmployeeById( UUID id );

  UserOutput doSignup( UserInput input );

  LogInOutput doLogin( LoginInput input );

  UserOutput getMe();

}
