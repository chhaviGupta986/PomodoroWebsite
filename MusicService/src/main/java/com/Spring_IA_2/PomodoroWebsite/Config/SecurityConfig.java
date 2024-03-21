package com.Spring_IA_2.PomodoroWebsite.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserInfoManagerConfig userInfoManagerConfig;

    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(webSecurityDebug);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userInfoManagerConfig)
                .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Order(1)
    @Bean
    public SecurityFilterChain api_uploadSongs_FilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.securityMatcher(new AntPathRequestMatcher("/api/uploadSongs","POST")).csrf(
                AbstractHttpConfigurer::disable
        ).authorizeHttpRequests(auth-> auth.anyRequest().authenticated()).userDetailsService(userInfoManagerConfig)
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
        }

    public SecurityFilterChain api_getSongsList_FilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.securityMatcher(new AntPathRequestMatcher("/api/getSongsList","GET"))
                .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/api/getSongsList")))
                .headers(headers -> headers.frameOptions(withDefaults()).disable())
                .build();
    }


    @Order(2)
    @Bean
    public SecurityFilterChain apiLoginFilterChain(HttpSecurity httpSecurity) throws Exception{


        return httpSecurity.securityMatcher(new AntPathRequestMatcher("/auth/**","POST")).csrf(
                        AbstractHttpConfigurer::disable
                ).authorizeHttpRequests(auth-> auth.anyRequest().permitAll()).userDetailsService(userInfoManagerConfig)
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Order(3)
    @Bean
    public SecurityFilterChain HomePageDisplayConfig(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher(("/")))
                .authorizeHttpRequests(auth->auth.anyRequest().permitAll())
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/")))
                .headers(headers -> headers.frameOptions(withDefaults()).disable())

                .build();
    }
}
