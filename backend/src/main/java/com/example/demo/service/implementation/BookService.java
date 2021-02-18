package com.example.demo.service.implementation;

import com.example.demo.dto.request.BookRequest;
import com.example.demo.dto.response.BookResponse;
import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Writer;
import com.example.demo.repository.IBookRepository;
import com.example.demo.repository.IGenreRepository;
import com.example.demo.repository.IWriterRepository;
import com.example.demo.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService implements IBookService {

    private final IBookRepository iBookRepository;

    private final IGenreRepository iGenreRepository;

    private final IWriterRepository iWriterRepository;

    public BookService(IBookRepository iBookRepository, IGenreRepository iGenreRepository, IWriterRepository iWriterRepository) {
        this.iBookRepository = iBookRepository;
        this.iGenreRepository = iGenreRepository;
        this.iWriterRepository = iWriterRepository;
    }

    @Override
    public void createBook(BookRequest request) {

        Book book = new Book();

        book.setTitle(request.getTitle());

        Genre genre  = iGenreRepository.findOneByName(request.getGenre());
        book.setGenre(genre);

        book.setPrice(request.getPrice());

        Writer writer = iWriterRepository.findOneByUsername(request.getWriter_id());
        book.setWriter(writer);

        book.setKeyWords(request.getKeyWords());

        Book createdBook = iBookRepository.save(book);
    }

    @Override
    public BookResponse getBook(UUID id) {

        Book book = iBookRepository.findOneById(id);
        BookResponse bookResponse = new BookResponse();

        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setGenre(book.getGenre().getName());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setKeyWords(book.getKeyWords());
        bookResponse.setWriter_id(book.getWriter().getUsername());

        return bookResponse;
    }

    @Override
    public List<BookResponse> getAllBooks() {

        List<Book> books = iBookRepository.findAllByDeleted(false);
        List<BookResponse> bookResponses = new ArrayList<>();

        for (Book b : books) {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(b.getId());
            bookResponse.setTitle(b.getTitle());
            bookResponse.setGenre(b.getGenre().getName());
            bookResponse.setKeyWords(b.getKeyWords());
            bookResponse.setWriter_id(b.getWriter().getUsername());
            bookResponse.setPrice(b.getPrice());
            bookResponses.add(bookResponse);
        }

        return bookResponses;
    }

    @Override
    public List<BookResponse> getAllBooksByWriter(String username) {

        List<Book> books = iBookRepository.findAllByDeletedAndWriter_Username(false, username);
        List<BookResponse> bookResponses = new ArrayList<>();

        for (Book b : books) {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(b.getId());
            bookResponse.setTitle(b.getTitle());
            bookResponse.setGenre(b.getGenre().getName());
            bookResponse.setKeyWords(b.getKeyWords());
            bookResponse.setWriter_id(b.getWriter().getUsername());
            bookResponse.setPrice(b.getPrice());
            bookResponses.add(bookResponse);
        }

        return bookResponses;
    }

    @Override
    public BookResponse updateBook(UUID id, BookRequest request) {

        Book book = iBookRepository.findOneById(id);

        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());

        iBookRepository.save(book);

        BookResponse bookResponse = new BookResponse();

        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setGenre(book.getGenre().getName());
        bookResponse.setKeyWords(book.getKeyWords());
        bookResponse.setWriter_id(book.getWriter().getUsername());
        bookResponse.setPrice(book.getPrice());

        return bookResponse;
    }

    @Override
    public void deleteBook(UUID id) {

        Book book = iBookRepository.findOneById(id);

        book.setDeleted(true);

        iBookRepository.save(book);
    }

}
