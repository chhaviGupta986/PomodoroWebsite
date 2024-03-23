package com.Authentication.Auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.Authentication.Auth.Entities.Roles;
import com.Authentication.Auth.Entities.Token;
import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.Services.JWTService;
import com.Authentication.Auth.Services.UserInfoService;
import com.Authentication.Auth.classes.UserDetailService;
import com.Authentication.Auth.filter.JWTFilter;
import com.Authentication.Auth.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses ={UserRepository.class, Roles.class, Token.class} )
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
