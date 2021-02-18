package com.example.demo.service;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.UserResponse;

import java.util.List;

public interface IWriterService {

    UserResponse getWriter(String username);

    List<UserResponse> getAllWriters();

    UserResponse updateWriter(String username, CreateUserRequest writerRequest);

    void deleteWriter(String username);
}
