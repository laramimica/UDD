package com.example.demo.service.implementation;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Book;
import com.example.demo.entity.Reader;
import com.example.demo.entity.User;
import com.example.demo.entity.Writer;
import com.example.demo.repository.IReaderRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.IWriterRepository;
import com.example.demo.service.IAuthService;
import com.example.demo.utils.UserType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository iUserRepository;

    private final IReaderRepository iReaderRepository;

    private final IWriterRepository iWriterRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(IUserRepository iUserRepository, IReaderRepository iReaderRepository, IWriterRepository iWriterRepository, PasswordEncoder passwordEncoder) {
        this.iUserRepository = iUserRepository;
        this.iReaderRepository = iReaderRepository;
        this.iWriterRepository = iWriterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(CreateUserRequest createUserRequest) throws Exception {

        if(iUserRepository.findByUsername(createUserRequest.getUsername()).isPresent()) {
            throw new Exception("Već postoji korisnik sa unetim korisničkim imenom.");
        }

        if(iUserRepository.findByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new Exception("Već postoji korisnik sa unetom email adresom.");
        }

        if(createUserRequest.getIsWriter()) {
            Writer writer = new Writer();

            writer.setUsername(createUserRequest.getUsername());
            writer.setUserType(UserType.WRITER);
            writer.setFirstName(createUserRequest.getFirstName());
            writer.setLastName(createUserRequest.getLastName());
            writer.setCity(createUserRequest.getCity());
            writer.setCountry(createUserRequest.getCountry());
            writer.setEmail(createUserRequest.getEmail());
            writer.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            writer.setDeleted(false);

            iWriterRepository.save(writer);
        }
        else {
            Reader reader = new Reader();

            reader.setUsername(createUserRequest.getUsername());
            reader.setUserType(UserType.READER);
            reader.setFirstName(createUserRequest.getFirstName());
            reader.setLastName(createUserRequest.getLastName());
            reader.setCity(createUserRequest.getCity());
            reader.setCountry(createUserRequest.getCountry());
            reader.setEmail(createUserRequest.getEmail());
            reader.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            reader.setDeleted(false);

            iReaderRepository.save(reader);
        }
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) throws Exception {

        User user = iUserRepository.findOneByUsername(loginRequest.getUsername());

        if(user == null) {
            throw new Exception("Unete su loše akreditive.");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new Exception("Unete su loše akreditive.");
        }

        if(user.isDeleted()){
            throw new Exception("Nalog nije aktivan.");
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCity(user.getCity());
        userResponse.setCountry(user.getCountry());
        userResponse.setPassword(user.getPassword());
        userResponse.setUserType(user.getUserType());

        if(user.getUserType().equals(UserType.WRITER)) {
            Writer writer = iWriterRepository.findOneByUsername(user.getUsername());
            for (Book book : writer.getBooks()) {
                userResponse.getBooks().add(book.getTitle());
            }
        }

        return userResponse;
    }
}
