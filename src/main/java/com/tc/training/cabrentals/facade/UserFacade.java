package com.tc.training.cabrentals.facade;

import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;
import com.tc.training.cabrentals.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserFacade {
     UserOutput createEmployee(UserInput input);
     List<UserOutput> getAllEmployee();
     UserOutput getEmployeeById(UUID id);
     void deleteEmployeeById(UUID id);
}
