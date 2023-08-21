package com.tc.training.cabrentals.facade.impl;

import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;
import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
import com.tc.training.cabrentals.facade.UserFacade;
import com.tc.training.cabrentals.services.FirebaseUserService;
import com.tc.training.cabrentals.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacadeIml implements UserFacade {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final FirebaseUserService firebaseUserService;
    @Override
    public UserOutput createEmployee(UserInput input) {
        User map = modelMapper.map(input, User.class);
        map.setRole(Role.ADMIN);
        String firebaseUserId = firebaseUserService.createUser(input);
        map.setFirebaseId(firebaseUserId);
        User user = userService.create(map);
        UserOutput output = modelMapper.map(user, UserOutput.class);
        return output;
    }
}
