package com.demo.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class DemoController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/dashboard/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> userDashboard() {
        return new ResponseEntity<>("User Dashboard", HttpStatus.OK);
    }

    @GetMapping("/dashboard/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminDashboard() {
        return new ResponseEntity<>("Admin Dashboard", HttpStatus.OK);
    }

}
