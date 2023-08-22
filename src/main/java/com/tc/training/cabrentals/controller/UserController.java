package com.tc.training.cabrentals.controller;

import com.tc.training.cabrentals.dto.UserInput;
import com.tc.training.cabrentals.dto.UserOutput;
import com.tc.training.cabrentals.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;
    @PostMapping
    public UserOutput createEmployee(@RequestBody UserInput input) {
        return userFacade.createEmployee(input);
    }
    @GetMapping
    public List<UserOutput> getAllEmployee(){
        return userFacade.getAllEmployee();
    }
    @GetMapping("/{id}")
    public UserOutput getEmployeeById(@PathVariable UUID id){
        return userFacade.getEmployeeById(id);
    }
    @DeleteMapping
    public void deleteEmployeeById(@RequestParam UUID id){
        userFacade.deleteEmployeeById(id);
    }
}
