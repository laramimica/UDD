package com.example.demo.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {

    private String username;

    private String firstName;

    private String lastName;

    private String city;

    private String country;

    private String email;

    private String password;

    private Boolean isWriter;

    private List<String> genres;
}
