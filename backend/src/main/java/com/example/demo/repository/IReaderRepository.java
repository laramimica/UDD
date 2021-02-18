package com.example.demo.repository;

import com.example.demo.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReaderRepository extends JpaRepository<Reader, String>  {

    Reader findOneByUsername(String username);

    List<Reader> findAllByDeleted(Boolean deleted);
}
