package com.example.demo.service;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.UserResponse;

public interface IAuthService {

    void register(CreateUserRequest createUserRequest) throws Exception;

    UserResponse login(LoginRequest loginRequest) throws Exception;
}
