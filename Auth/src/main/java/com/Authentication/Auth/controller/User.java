package com.Authentication.Auth.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final UserInfoService userInfoService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JWTService jwtService;

    public User(UserInfoService userInfoService, JWTService jwtService) {
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value ="/createUser",method = RequestMethod.POST)
    public ResponseEntity<usersResponse>createUsers(@ModelAttribute("UserInfo") UserInfo info){

        UserInfo userInfo = userInfoService.fetchUser(info.getEmail());
        if(userInfo == null){

            UserInfo userInfo_db = new UserInfo();
            userInfo_db.setUsername(info.getUsername());
            userInfo_db.setEmail(info.getEmail());
            userInfo_db.setPassword(passwordEncoder.encode(info.getPassword()));
            userInfo_db.setMobileNumber(info.getMobileNumber());
            userInfo_db.setRoles(info.getRoles());
            userInfoService.saveUser(userInfo_db);

            UserInfo userInfo1 = userInfoService.saveUser(userInfo_db);
            usersResponse usersResponse = new usersResponse(userInfo1.getUsername(),userInfo1.getEmail(),userInfo1.getMobileNumber(),userInfo1.getRoles());
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.valueOf(400));

    }




}
