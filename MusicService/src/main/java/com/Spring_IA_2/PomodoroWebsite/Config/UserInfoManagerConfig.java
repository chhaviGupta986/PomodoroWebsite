package com.Spring_IA_2.PomodoroWebsite.Config;

import com.Spring_IA_2.PomodoroWebsite.classes.Users.Users;
import com.Spring_IA_2.PomodoroWebsite.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class UserInfoManagerConfig implements UserDetailsService {
    private final UsersService usersService;
    private static final Logger logger = LoggerFactory.getLogger(UserInfoManagerConfig.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user: {}", username); // Log username
        try
        {
            ResponseEntity<Users> responseEntity = usersService.getUsersByEmail(username);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                Users users = responseEntity.getBody();
                return new UserInfoConfig(users);
            }else
            {
                throw new UsernameNotFoundException("Email not found !!");
            }
        } catch
        (ExecutionException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }


    }
}
