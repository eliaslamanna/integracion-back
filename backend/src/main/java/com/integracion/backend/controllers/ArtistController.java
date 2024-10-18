package com.integracion.backend.controllers;


import com.integracion.backend.controllers.request.ArtistSearchParametersRequest;
import com.integracion.backend.controllers.request.CreateArtistRequest;
import com.integracion.backend.controllers.request.UpdateArtistRequest;
import com.integracion.backend.dto.ArtistDTO;
import com.integracion.backend.services.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/getArtists")
    public ResponseEntity<List<ArtistDTO>> getArtists() {
        List<ArtistDTO> artists = artistService.getAllArtists();
        return ok(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getById(@PathVariable String id) {
        ArtistDTO artist = artistService.getById(id);
        return ok(artist);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArtistDTO>> getById(ArtistSearchParametersRequest searchRequest) {
        List<ArtistDTO> artists = artistService.search(searchRequest);
        return ok(artists);
    }

    @PostMapping("/createArtist")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody CreateArtistRequest artistRequest) {
        var createdArtist = artistService.addArtist(artistRequest);
        return new ResponseEntity<>(createdArtist, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable String id, @RequestBody UpdateArtistRequest artistRequest) {
        var updatedArtist = artistService.updateArtist(id, artistRequest);
        return ok(updatedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeArtist(@PathVariable String id) {
        artistService.deleteArtist(id);
        return noContent().build();
    }

}