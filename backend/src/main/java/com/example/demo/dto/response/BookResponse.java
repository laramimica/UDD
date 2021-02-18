package com.example.demo.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private UUID id;

    private String title;

    private String keyWords;

    private float price;

    private String genre;

    private String writer_id;
}
