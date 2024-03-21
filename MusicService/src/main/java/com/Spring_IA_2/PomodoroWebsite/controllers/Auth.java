package com.Spring_IA_2.PomodoroWebsite.controllers;
import java.util.concurrent.ExecutionException;
import com.Spring_IA_2.PomodoroWebsite.classes.Users.userResponse;
import com.Spring_IA_2.PomodoroWebsite.classes.UsersLogin.UserLogin;
import com.Spring_IA_2.PomodoroWebsite.classes.createUsers.createUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Spring_IA_2.PomodoroWebsite.services.UsersService;

@RestController
@RequestMapping(path = "/auth")
public class Auth {
    UsersService usersService;


    @Autowired
    public Auth(UsersService usersService){
        this.usersService = usersService;

    }

    @RequestMapping(path = "/createUser",method = RequestMethod.POST)
    public ResponseEntity<String> createUsers(@RequestBody createUsers user) throws ExecutionException, InterruptedException {
        userResponse res = usersService.createUsers(user);
        if(res == null){
            return new ResponseEntity<>("Error user already exists :'D ", HttpStatus.valueOf(400));
        }
        return new ResponseEntity<>("User created successfully :D", HttpStatus.valueOf(200));
    }
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public ResponseEntity<userResponse> getUsers(@RequestBody UserLogin user) throws ExecutionException, InterruptedException {

        userResponse user_db =  usersService.getUser(user);
        if (user_db != null){
            return new ResponseEntity<>(user_db, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(400));
    }


}


