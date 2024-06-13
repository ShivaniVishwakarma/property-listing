package com.engelandvolkers.property_listing.services;


import com.engelandvolkers.property_listing.config.LoginProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginProperties loginProperties;

    public boolean validateLogin(String email, String password) {
        return loginProperties.getEmail().equals(email) && loginProperties.getPassword().equals(password);
    }
}

