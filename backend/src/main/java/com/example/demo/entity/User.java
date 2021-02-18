package com.example.demo.entity;

import com.example.demo.utils.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "firstName", nullable = false, unique = false)
    private String firstName;

    @Column(name = "lastName", nullable = false, unique = false)
    private String lastName;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @Column(nullable = false)
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
