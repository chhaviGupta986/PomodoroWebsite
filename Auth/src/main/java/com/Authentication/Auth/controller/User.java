package com.Authentication.Auth.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.Services.JWTService;
import com.Authentication.Auth.Services.UserInfoService;
import com.Authentication.Auth.classes.usersResponse;

/**
 * Status Code: 400 -> User Exists error
 * */
@RestController
@RequestMapping(value = "/auth")
public class User {

    @GetMapping(value = "/profile")
    public String getProfile(){
        return "Hi";
    }





}
