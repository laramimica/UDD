package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private String filename;

    private String writer;

    private String genres;

    private String title;

    private String keywords;

//    private String text;
}
