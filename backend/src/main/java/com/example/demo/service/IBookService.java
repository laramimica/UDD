package com.example.demo.service;

import com.example.demo.dto.request.BookRequest;
import com.example.demo.dto.response.BookResponse;

import java.util.List;
import java.util.UUID;

public interface IBookService {

    void createBook(BookRequest request);

    BookResponse getBook(UUID id);

    List<BookResponse> getAllBooks();

    List<BookResponse> getAllBooksByWriter(String name);

    BookResponse updateBook(UUID id, BookRequest request);

    void deleteBook(UUID id);
}
