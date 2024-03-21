package com.Spring_IA_2.PomodoroWebsite.controllers;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @RequestMapping(method = RequestMethod.GET,path = "/")
    public String home(Authentication authentication){
        return authentication.getName();
    }
}
