package com.example.demo.service.implementation;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.ReaderResponse;
import com.example.demo.entity.Reader;
import com.example.demo.repository.IReaderRepository;
import com.example.demo.service.IReaderService;
import com.example.demo.utils.UserType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderService implements IReaderService {

    private final IReaderRepository iReaderRepository;

    public ReaderService(IReaderRepository iReaderRepository) {
        this.iReaderRepository = iReaderRepository;
    }

    @Override
    public ReaderResponse getReader(String username) {

        Reader reader = iReaderRepository.findOneByUsername(username);

        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setFirstName(reader.getFirstName());
        readerResponse.setLastName(reader.getLastName());
        readerResponse.setCity(reader.getCity());
        readerResponse.setCountry(reader.getCountry());
        readerResponse.setEmail(reader.getEmail());
        readerResponse.setUsername(reader.getUsername());
        readerResponse.setPassword(reader.getPassword());

        return readerResponse;
    }

    @Override
    public List<ReaderResponse> getAllReaders() {

        List<Reader> readers = iReaderRepository.findAllByDeleted(false);
        List<ReaderResponse> readerResponses = new ArrayList<>();

        for(Reader reader : readers) {
            ReaderResponse readerResponse = new ReaderResponse();
            readerResponse.setFirstName(reader.getFirstName());
            readerResponse.setLastName(reader.getLastName());
            readerResponse.setCity(reader.getCity());
            readerResponse.setCountry(reader.getCountry());
            readerResponse.setEmail(reader.getEmail());
            readerResponse.setUsername(reader.getUsername());
            readerResponse.setPassword(reader.getPassword());

            readerResponses.add(readerResponse);
        }

        return readerResponses;
    }

    @Override
    public ReaderResponse updateReader(String username, CreateUserRequest readerRequest) {

        Reader reader = iReaderRepository.findOneByUsername(username);
        reader.setFirstName(readerRequest.getFirstName());
        reader.setLastName(readerRequest.getLastName());
        reader.setCity(readerRequest.getCity());
        reader.setCountry(readerRequest.getCountry());
        reader.setEmail(readerRequest.getEmail());

        iReaderRepository.save(reader);

        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setFirstName(reader.getFirstName());
        readerResponse.setLastName(reader.getLastName());
        readerResponse.setCity(reader.getCity());
        readerResponse.setCountry(reader.getCountry());
        readerResponse.setEmail(reader.getEmail());

        return readerResponse;

    }

    @Override
    public void deleteReader(String username) {

        Reader reader = iReaderRepository.findOneByUsername(username);
        reader.setDeleted(true);
        iReaderRepository.save(reader);

    }

}
