package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends ElasticsearchRepository<Book, String> {

}
