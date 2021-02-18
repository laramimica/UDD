package com.example.demo.controller;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.ReaderResponse;
import com.example.demo.service.IReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final IReaderService iReaderService;

    public ReaderController(IReaderService iReaderService) {
        this.iReaderService = iReaderService;
    }

    @GetMapping("/{username}")
    public ReaderResponse getReader(@PathVariable String username) {
        return iReaderService.getReader(username);
    }

    @GetMapping
    public List<ReaderResponse> getAllReaders() {
        return iReaderService.getAllReaders();
    }

    @PutMapping("/{username}")
    public ReaderResponse updateReader(@PathVariable String username, @RequestBody CreateUserRequest readerRequest) {
        return iReaderService.updateReader(username, readerRequest);
    }

    @DeleteMapping("/{username}")
    public void deleteReader(@PathVariable String username) {
        iReaderService.deleteReader(username);
    }
}
