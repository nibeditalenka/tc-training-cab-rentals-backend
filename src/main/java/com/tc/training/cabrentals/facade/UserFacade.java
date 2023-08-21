package com.tc.training.cabrentals.facade;

import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;

public interface UserFacade {
     UserOutput createEmployee(UserInput input);
}
