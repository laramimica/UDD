package com.example.demo.repository;

import com.example.demo.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWriterRepository extends JpaRepository<Writer, String> {

}
