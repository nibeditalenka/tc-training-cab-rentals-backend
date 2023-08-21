package com.tc.training.cabrentals.controller;

import com.tc.training.cabrentals.dto.AddressInput;
import com.tc.training.cabrentals.facade.CenterFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center")
@RequiredArgsConstructor
class CenterController {
    private final CenterFacade centerFacade;
    @PostMapping
    public void add(@RequestBody AddressInput address){
     centerFacade.add(address);
    }
}
