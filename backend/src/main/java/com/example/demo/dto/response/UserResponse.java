package com.example.demo.dto.response;

import com.example.demo.utils.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String username;

    private String firstName;

    private String lastName;

    private String city;

    private String country;

    private String email;

    private String password;

    private List<String> books = new ArrayList<>(); //writer

    private UserType userType;
}
