package com.example.demo.service.implementation;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Book;
import com.example.demo.entity.Writer;
import com.example.demo.repository.IWriterRepository;
import com.example.demo.service.IWriterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WriterService implements IWriterService {

    private final IWriterRepository iWriterRepository;

    public WriterService(IWriterRepository iWriterRepository) {
        this.iWriterRepository = iWriterRepository;
    }

    @Override
    public UserResponse getWriter(String username) {

        Writer writer = iWriterRepository.findOneByUsername(username);

        UserResponse writerResponse = new UserResponse();
        writerResponse.setFirstName(writer.getFirstName());
        writerResponse.setLastName(writer.getLastName());
        writerResponse.setCity(writer.getCity());
        writerResponse.setCountry(writer.getCountry());
        writerResponse.setEmail(writer.getEmail());
        writerResponse.setUsername(writer.getUsername());
        writerResponse.setPassword(writer.getPassword());

        for (Book book : writer.getBooks()) {
            writerResponse.getBooks().add(book.getTitle());
        }

        return writerResponse;
    }

    @Override
    public List<UserResponse> getAllWriters() {

        List<Writer> writers = iWriterRepository.findAllByDeleted(false);
        List<UserResponse> writerResponses = new ArrayList<>();

        for (Writer writer : writers) {
            UserResponse writerResponse = new UserResponse();
            writerResponse.setFirstName(writer.getFirstName());
            writerResponse.setLastName(writer.getLastName());
            writerResponse.setCity(writer.getCity());
            writerResponse.setCountry(writer.getCountry());
            writerResponse.setEmail(writer.getEmail());
            writerResponse.setUsername(writer.getUsername());
            writerResponse.setPassword(writer.getPassword());

            for (Book book : writer.getBooks()) {
                writerResponse.getBooks().add(book.getTitle());
            }

            writerResponses.add(writerResponse);
        }

        return writerResponses;
    }

    @Override
    public UserResponse updateWriter(String username, CreateUserRequest writerRequest) {

        Writer writer = iWriterRepository.findOneByUsername(username);
        writer.setFirstName(writerRequest.getFirstName());
        writer.setLastName(writerRequest.getLastName());
        writer.setEmail(writerRequest.getEmail());
        writer.setCity(writerRequest.getCity());
        writer.setCountry(writerRequest.getCountry());

        iWriterRepository.save(writer);

        UserResponse writerResponse = new UserResponse();
        writerResponse.setFirstName(writer.getFirstName());
        writerResponse.setLastName(writer.getLastName());
        writerResponse.setCity(writer.getCity());
        writerResponse.setCountry(writer.getCountry());
        writerResponse.setEmail(writer.getEmail());
        writerResponse.setUsername(writer.getUsername());
        writerResponse.setPassword(writer.getPassword());

        for (Book book : writer.getBooks()) {
            writerResponse.getBooks().add(book.getTitle());
        }

        return writerResponse;
    }

    @Override
    public void deleteWriter(String username) {

        Writer writer = iWriterRepository.findOneByUsername(username);
        writer.setDeleted(true);
        iWriterRepository.save(writer);
    }
}
