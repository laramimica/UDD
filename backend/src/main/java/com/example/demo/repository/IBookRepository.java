package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IBookRepository extends JpaRepository<Book, UUID> {

    Book findOneById(UUID id);

    List<Book> findAllByDeleted(Boolean deleted);

    List<Book> findAllByDeletedAndWriter_Username(Boolean deleted, String username);
}
