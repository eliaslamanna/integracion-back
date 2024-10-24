package com.integracion.backend.services;

import com.integracion.backend.controllers.request.ArtistSearchParametersRequest;
import com.integracion.backend.controllers.request.CreateArtistRequest;
import com.integracion.backend.controllers.request.UpdateArtistRequest;
import com.integracion.backend.dto.ArtistDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.Artist;
import com.integracion.backend.model.Genre;
import com.integracion.backend.model.Image;
import com.integracion.backend.model.SocialMedia;
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

    private final GenreService genreService;

    private final SocialMediaService socialMediaService;

    private final ImageService imageService;

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
    public Artist findById(String id) {
        return artistRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
    }

    @Transactional
    public List<Artist> getAllByIdIn(List<String> ids) {
        return artistRepository.findAllById(ids.stream().map(UUID::fromString).toList());
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
    public ArtistDTO addArtist(CreateArtistRequest artistRequest) {
        Artist artist = modelMapper.map(artistRequest, Artist.class);

        if(!artistRequest.getGenreIds().isEmpty()) {
            List<Genre> genres = genreService.getAllByIdIn(artistRequest.getGenreIds());
            artist.setGenres(genres);
        }
        if(!artistRequest.getSocialMediaIds().isEmpty()) {
            List<SocialMedia> socialMedias = socialMediaService.getAllByIdIn(artistRequest.getSocialMediaIds());
            artist.setSocialMedia(socialMedias);
        }

        Artist savedArtist = artistRepository.save(artist);

        if (!artistRequest.getImageUrls().isEmpty()) {
            List<Image> imagesToSave = artistRequest.getImageUrls().stream()
                    .map(url -> Image.builder().url(url).artist(savedArtist).build())
                    .toList();

            imageService.saveAll(imagesToSave);
        }

        return modelMapper.map(savedArtist, ArtistDTO.class);
    }

    @Transactional
    public ArtistDTO updateArtist(String id, UpdateArtistRequest artistRequest) {
        Artist artistToUpdate = artistRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        modelMapper.map(artistRequest, artistToUpdate);

        if(!artistRequest.getGenreIds().isEmpty()) {
            List<Genre> genres = genreService.getAllByIdIn(artistRequest.getGenreIds());
            artistToUpdate.setGenres(genres);
        }
        if(!artistRequest.getSocialNetworkIds().isEmpty()) {
            List<SocialMedia> socialMedias = socialMediaService.getAllByIdIn(artistRequest.getSocialNetworkIds());
            artistToUpdate.setSocialMedia(socialMedias);
        }

        return modelMapper.map(artistRepository.save(artistToUpdate), ArtistDTO.class);
    }

    @Transactional
    public void deleteArtist(String id) {
        Artist artistToDelete = artistRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        artistRepository.delete(artistToDelete);
    }

}