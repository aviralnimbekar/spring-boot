package com.demo.springsecurity.service;

import com.demo.springsecurity.dto.ChangeRoleRequest;
import com.demo.springsecurity.dto.UserResponse;
import com.demo.springsecurity.dto.RegisterRequest;
import com.demo.springsecurity.model.UserEntity;
import com.demo.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {
        String role = userRepository.count() == 0 ? "ADMIN" : "USER";

        UserEntity user = new UserEntity();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(role);

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username already exists");
        }
//       TODO: model mapper can be used here
        return new UserResponse(user.getId(), user.getUsername(), user.getRole(), user.isEnabled());
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getRole(), user.isEnabled()))
                .toList();
    }

    public UserResponse updateRole(ChangeRoleRequest request) {
        UserEntity user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setRole(request.role());
        user = userRepository.save(user);
        return new UserResponse(user.getId(), user.getUsername(), user.getRole(), user.isEnabled());
    }
}
