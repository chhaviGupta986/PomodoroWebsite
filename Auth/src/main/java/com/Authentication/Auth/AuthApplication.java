package com.Authentication.Auth;

import Entities.Token;
import Services.JWTService;
import Services.UserInfoService;
import classes.UserDetailService;
import filter.JWTFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import Entities.UserInfo;
import Entities.Roles;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.UserRepository;

@SpringBootApplication
@EntityScan(basePackageClasses = {UserInfo.class, Roles.class, Token.class})
@ComponentScan(basePackageClasses = {controller.User.class, UserInfoService.class, JWTFilter.class, JWTService.class, UserDetailService.class,Services.AuthenticationService.class,controller.tutorial.class})
@EnableJpaRepositories(basePackageClasses ={UserRepository.class, Roles.class, Token.class} )
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
