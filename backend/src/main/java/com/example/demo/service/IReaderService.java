package com.example.demo.service;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.ReaderResponse;

import java.util.List;

public interface IReaderService {

    ReaderResponse getReader(String username);

    List<ReaderResponse> getAllReaders();

    ReaderResponse updateReader(String username, CreateUserRequest readerRequest);

    void deleteReader(String username);
}
