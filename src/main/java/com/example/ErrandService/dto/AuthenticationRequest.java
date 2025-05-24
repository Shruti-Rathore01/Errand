package com.example.ErrandService.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
} 