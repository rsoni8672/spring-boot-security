package com.rahul.springjwt.service.auth.user;

import com.rahul.springjwt.security.service.UserDetailsImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    UserDetailsImpl findByUserName(String username) throws UsernameNotFoundException;
}
