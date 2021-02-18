package com.example.demo.repository;

import com.example.demo.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGenreRepository extends JpaRepository<Genre, String> {

    Genre findOneByName(String name);

    List<Genre> findAllByDeleted(Boolean deleted);
}
