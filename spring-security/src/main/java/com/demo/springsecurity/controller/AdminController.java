package com.demo.springsecurity.controller;

import com.demo.springsecurity.dto.ChangeRoleRequest;
import com.demo.springsecurity.dto.UserResponse;
import com.demo.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Admin";
    }

    @PutMapping("/change-role")
    public UserResponse updateRole(@RequestBody ChangeRoleRequest request) {
        return userService.updateRole(request);
    }
}
