package com.integracion.backend.controllers;


import com.integracion.backend.controllers.request.CreateGenreRequest;
import com.integracion.backend.dto.GenreDTO;
import com.integracion.backend.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/getGenres")
    public ResponseEntity<List<GenreDTO>> getGenres() {
        List<GenreDTO> genres = genreService.getListGenres();
        return ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable String id) {
        GenreDTO genre = genreService.getById(id);
        return ok(genre);
    }

    @PostMapping("/createGenre")
    public ResponseEntity<GenreDTO> createGenre(@RequestBody CreateGenreRequest genreRequest) {
        GenreDTO genre = genreService.addGenre(genreRequest);
        return new ResponseEntity<>(genre, CREATED);
    }

}