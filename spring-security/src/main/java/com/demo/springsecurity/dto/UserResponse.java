package com.demo.springsecurity.dto;

public record UserResponse(Long id, String username, String role, boolean enabled) {
}
