package com.rahul.springjwt.controller;

import com.rahul.springjwt.model.dto.LoginDto;
import com.rahul.springjwt.model.dto.SignUpDto;
import com.rahul.springjwt.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    @Autowired
    AuthService authService;

    @PostMapping(value =  "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String jwtToken = authService.login(loginDto.getUserName(), loginDto.getPassword());
        return ResponseEntity.ok().body(jwtToken);
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        authService.signUp(signUpDto.getUserName(), signUpDto.getPassword(), signUpDto.getRoles());
        return ResponseEntity.ok().body("");
    }
}
