package com.integracion.backend.controllers;


import com.integracion.backend.controllers.request.ArtistSearchParametersRequest;
import com.integracion.backend.dto.ArtistDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.services.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/getArtists")
    public ResponseEntity<List<ArtistDTO>> getArtists() {
        try {
            List<ArtistDTO> artists = artistService.getAllArtists();
            return ok(artists);
        }
        catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getById(@PathVariable String id) {
        try {
            ArtistDTO artist = artistService.getById(id);

            return ok(artist);
        }
        catch (ItemNotFoundException ie) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArtistDTO>> getById(ArtistSearchParametersRequest searchRequest) {
        try {
            List<ArtistDTO> artists = artistService.search(searchRequest);

            return ok(artists);
        }
        catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artist) {
        try{
            var createdArtist = artistService.addArtist(artist);
            return new ResponseEntity<>(createdArtist, CREATED);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

}