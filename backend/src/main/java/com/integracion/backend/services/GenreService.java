package com.integracion.backend.services;

import com.integracion.backend.controllers.request.CreateGenreRequest;
import com.integracion.backend.dto.GenreDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.Genre;
import com.integracion.backend.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<GenreDTO> getListGenres() {
        return genreRepository.findAll()
                .stream()
                .map(genre -> modelMapper.map(genre, GenreDTO.class))
                .toList();
    }

    @Transactional
    public GenreDTO getById(String id) {
        var genre = genreRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(genre, GenreDTO.class);
    }

    @Transactional
    public List<Genre> getAllByIdIn(List<String> ids) {
        return genreRepository.findAllById(ids.stream().map(UUID::fromString).toList());
    }

    @Transactional
    public GenreDTO addGenre(CreateGenreRequest genreRequest) {
        var genre = modelMapper.map(genreRequest, Genre.class);
        return modelMapper.map(genreRepository.save(genre), GenreDTO.class);
    }

}