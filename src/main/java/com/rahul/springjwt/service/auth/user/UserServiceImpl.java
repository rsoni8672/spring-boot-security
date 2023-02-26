package com.rahul.springjwt.service.auth.user;

import com.rahul.springjwt.repository.UserRepository;
import com.rahul.springjwt.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service(value = "UserService")
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return UserDetailsImpl.build(userRepository.findByUsername(username).get());
        }
        catch (NoSuchElementException exception){
            String exceptionMessage = "Invalid username passed.";
            throw new UsernameNotFoundException(exceptionMessage);
        }
    }
}
