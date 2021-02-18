package com.example.demo.controller;

import com.example.demo.dto.request.BookRequest;
import com.example.demo.dto.response.BookResponse;
import com.example.demo.service.IBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final IBookService iBookService;

    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBook(@RequestBody BookRequest request) {
        iBookService.createBook(request);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return iBookService.getAllBooks();
    }

    @GetMapping("/{username}/writer")
    public List<BookResponse> getAllBooksByWriter(@PathVariable String username) {
        return iBookService.getAllBooksByWriter(username);
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable UUID id) {
        return iBookService.getBook(id);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable UUID id, @RequestBody BookRequest request) {
        return iBookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        iBookService.deleteBook(id);
    }

}
