package com.engelandvolkers.property_listing.controllers;

import com.engelandvolkers.property_listing.dtos.LoginRequest;
import com.engelandvolkers.property_listing.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (loginRequest.getEmail().endsWith("@test.com")) {
            return new ResponseEntity<>("E-Mails from test.com are invalid", HttpStatus.BAD_REQUEST);
        }

        if (!loginService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword())) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}



