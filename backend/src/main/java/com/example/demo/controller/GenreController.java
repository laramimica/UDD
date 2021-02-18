package com.example.demo.controller;

import com.example.demo.dto.request.GenreRequest;
import com.example.demo.dto.response.GenreResponse;
import com.example.demo.service.IGenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final IGenreService iGenreService;

    public GenreController(IGenreService iGenreService) {
        this.iGenreService = iGenreService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createGenre(@RequestBody GenreRequest request) {
        iGenreService.createGenre(request);
    }

    @GetMapping("/{name}")
    public GenreResponse getGenre(@PathVariable String name) {
        return iGenreService.getGenre(name);
    }

    @GetMapping
    public List<GenreResponse> getAllGenres() {
        return iGenreService.getAllGenres();
    }

    @PutMapping("/{name}")
    public GenreResponse updateGenre(@PathVariable String name) {
        return iGenreService.updateGenre(name);
    }

    @DeleteMapping("/{name}")
    public void deleteGenre(@PathVariable String name) {
        iGenreService.deleteGenre(name);
    }

}
