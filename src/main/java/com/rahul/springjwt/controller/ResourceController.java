package com.rahul.springjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping(value = "/api/resource")
public class ResourceController {
    @GetMapping(value = "/get-resource")
    @PreAuthorize("hasAuthority('DEV')")
    public ResponseEntity<?> getResource(){
        return ResponseEntity.ok().body("YAYYYY");
    }
}
