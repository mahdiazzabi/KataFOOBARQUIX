package com.mazzabi.KataFOOBARQUIX.controller;

import com.mazzabi.KataFOOBARQUIX.service.FooBarQuixServiceImpl;
import com.mazzabi.KataFOOBARQUIX.service.IFooBarQuixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FooBarQuixController {

    private final IFooBarQuixService fooBarQuixService;

    public FooBarQuixController(IFooBarQuixService fooBarQuixService) {
        this.fooBarQuixService = fooBarQuixService;
    }

    @GetMapping("/transform/{number}")
    public ResponseEntity<String> transform(@PathVariable int number) {
        return (number < 0 || number > 100) ?
                ResponseEntity.badRequest().body("Number must be between 0 and 100") :
                ResponseEntity.ok(fooBarQuixService.transform(number));
    }
}
