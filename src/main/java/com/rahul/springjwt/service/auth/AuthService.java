package com.rahul.springjwt.service.auth;

import java.util.List;

public interface AuthService {

    public String login(String userName, String password);
    void signUp(String userName, String password, List<String> roles);
}
