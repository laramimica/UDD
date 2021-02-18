package com.example.demo.controller;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.IWriterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/writers")
public class WriterController {

    private final IWriterService iWriterService;

    public WriterController(IWriterService iWriterService) {
        this.iWriterService = iWriterService;
    }

    @GetMapping("/{username}")
    public UserResponse getWriter(@PathVariable String username) {
        return iWriterService.getWriter(username);
    }

    @GetMapping
    public List<UserResponse> getAllWriters() {
        return iWriterService.getAllWriters();
    }

    @PutMapping("/{username}")
    public UserResponse updateWriter(@PathVariable String username, @RequestBody CreateUserRequest writerRequest) {
        return iWriterService.updateWriter(username, writerRequest);
    }

    @DeleteMapping("/{username}")
    public void deleteWriter(@PathVariable String username) {
        iWriterService.deleteWriter(username);
    }
}
