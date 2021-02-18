package com.example.demo.service;

import com.example.demo.dto.request.GenreRequest;
import com.example.demo.dto.response.GenreResponse;

import java.util.List;

public interface IGenreService {

    void createGenre(GenreRequest request);

    GenreResponse getGenre(String name);

    List<GenreResponse> getAllGenres();

    GenreResponse updateGenre(String name);

    void deleteGenre(String name);
}
