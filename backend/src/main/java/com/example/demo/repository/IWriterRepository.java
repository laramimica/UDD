package com.example.demo.repository;

import com.example.demo.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWriterRepository extends JpaRepository<Writer, String> {

    Writer findOneByUsername(String username);

    List<Writer> findAllByDeleted(Boolean deleted);
}
