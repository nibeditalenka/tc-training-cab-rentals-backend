package com.tc.training.cabrentals.facade;

import java.util.List;
import java.util.UUID;

import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;

public interface UserFacade {
  UserOutput createEmployee( UserInput input );

  List<UserOutput> getAllEmployee();

  UserOutput getEmployeeById( UUID id );

  void deleteEmployeeById( UUID id );

  UserOutput doSignup( UserInput input );
}
