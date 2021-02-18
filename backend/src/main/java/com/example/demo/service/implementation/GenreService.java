package com.example.demo.service.implementation;

import com.example.demo.dto.request.GenreRequest;
import com.example.demo.dto.response.GenreResponse;
import com.example.demo.entity.Genre;
import com.example.demo.repository.IGenreRepository;
import com.example.demo.service.IGenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService implements IGenreService {

    private final IGenreRepository iGenreRepository;

    public GenreService(IGenreRepository iGenreRepository) {
        this.iGenreRepository = iGenreRepository;
    }

    @Override
    public void createGenre(GenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.getName());
        iGenreRepository.save(genre);
    }

    @Override
    public GenreResponse getGenre(String name) {

        Genre genre = iGenreRepository.findOneByName(name);
        GenreResponse genreResponse = new GenreResponse(genre.getName());

        return genreResponse;
    }

    @Override
    public List<GenreResponse> getAllGenres() {

        List<Genre> genres = iGenreRepository.findAllByDeleted(false);
        List<GenreResponse> genreResponses = new ArrayList<>();

        for (Genre genre: genres) {
            GenreResponse genreResponse = new GenreResponse(genre.getName());
            genreResponses.add(genreResponse);
        }

        return genreResponses;
    }

    @Override
    public GenreResponse updateGenre(String name) {

        Genre genre = iGenreRepository.findOneByName(name);
        genre.setName(name);
        iGenreRepository.save(genre);

        GenreResponse genreResponse = new GenreResponse();
        genreResponse.setName(name);

        return genreResponse;
    }

    @Override
    public void deleteGenre(String name) {

        Genre genre = iGenreRepository.findOneByName(name);
        genre.setDeleted(true);
        iGenreRepository.save(genre);
    }
}
