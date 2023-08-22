package com.tc.training.cabrentals.controller;

import com.tc.training.cabrentals.dto.CenterInput;
import com.tc.training.cabrentals.entities.Center;
import com.tc.training.cabrentals.facade.CenterFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/center")
@RequiredArgsConstructor
class CenterController {
    private final CenterFacade centerFacade;
    @PostMapping
    public void add(@RequestBody CenterInput centerInput){
     centerFacade.add(centerInput);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        centerFacade.delete(id);
    }

    @GetMapping
    public List<Center> getAll(){
        return centerFacade.getAll();
    }
}
