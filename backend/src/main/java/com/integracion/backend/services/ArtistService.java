package com.integracion.backend.services;

import com.integracion.backend.controllers.request.ArtistSearchParametersRequest;
import com.integracion.backend.controllers.request.CreateArtistRequest;
import com.integracion.backend.controllers.request.UpdateArtistRequest;
import com.integracion.backend.dto.ArtistDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.Artist;
import com.integracion.backend.repository.ArtistRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private EntityManager entityManager;

    private final ModelMapper modelMapper;

    @Transactional
    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(artist -> modelMapper.map(artist, ArtistDTO.class)).toList();
    }

    @Transactional
    public ArtistDTO getById(String id) {
        var artist = artistRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(artist, ArtistDTO.class);
    }

    @Transactional
    public List<ArtistDTO> search(ArtistSearchParametersRequest artistDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);

        Root<Artist> artistRoot = cq.from(Artist.class);
        List<Predicate> predicates = new ArrayList<>();

        if (artistDTO.getArtisticName() != null && !artistDTO.getArtisticName().isEmpty()) {
            predicates.add(cb.like(cb.lower(artistRoot.get("artisticName")), "%" + artistDTO.getArtisticName().toLowerCase() + "%"));
            cq.orderBy(cb.asc(cb.length(artistRoot.get("artisticName"))));
        }

        // futuros criterios de busqueda

        cq.where(predicates.toArray(new Predicate[0]));

        List<Artist> artists = entityManager.createQuery(cq).getResultList();

        return artists.stream().map(artist -> modelMapper.map(artist, ArtistDTO.class)).toList();
    }

    @Transactional
    public ArtistDTO addArtist(CreateArtistRequest artistDTO) {
        Artist artist = modelMapper.map(artistDTO, Artist.class);
        return modelMapper.map(artistRepository.save(artist), ArtistDTO.class);
    }

    @Transactional
    public ArtistDTO updateArtist(String id, UpdateArtistRequest artistDTO) {
        Artist artistToUpdate = artistRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        modelMapper.map(artistDTO, artistToUpdate);
        return modelMapper.map(artistRepository.save(artistToUpdate), ArtistDTO.class);
    }

}