package com.example.demo.dto.request;

import lombok.Data;

@Data
public class BookRequest {

    private String title;

    private String genre;

    private float price;

    private String keyWords;

    private String writer_id;
}
