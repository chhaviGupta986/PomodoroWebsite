package com.Spring_IA_2.PomodoroWebsite.classes.UsersLogin;

import java.io.Serializable;

public class UserLogin implements Serializable {

    private String name;
    private String email;

    public UserLogin(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserLogin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
