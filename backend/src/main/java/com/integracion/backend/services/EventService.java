package com.integracion.backend.services;

import com.integracion.backend.controllers.request.CreateEventRequest;
import com.integracion.backend.controllers.request.UpdateEventRequest;
import com.integracion.backend.dto.EventDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.Artist;
import com.integracion.backend.model.Event;
import com.integracion.backend.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.fromString;

@Service
@AllArgsConstructor
public class EventService {

    private final ArtistService artistService;

    private final EventRepository eventRepository;

    private final EventConverter eventConverter;

    private  final ModelMapper modelMapper;

    @Transactional
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventConverter::mapToDTO).toList();
    }

    @Transactional
    public EventDTO getById(String id) {
        Event event = eventRepository.findById(fromString(id)).orElseThrow(ItemNotFoundException::new);
        return eventConverter.mapToDTO(event);
    }

    @Transactional
    public List<EventDTO> getProximosEventos() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthFromNow = today.plusMonths(1);
        return eventRepository.findByDateBetween(today, oneMonthFromNow).stream().map(eventConverter::mapToDTO).toList();
    }

    @Transactional
    public EventDTO createEvent(CreateEventRequest eventRequest) {
        Event event = eventConverter.mapToEvent(eventRequest);

        if(!eventRequest.getArtistIds().isEmpty()) {
            List<Artist> artists = artistService.getAllByIdIn(eventRequest.getArtistIds());
            event.setArtists(artists);
        }

        return eventConverter.mapToDTO(eventRepository.save(event));
    }

    @Transactional
    public EventDTO updateEvent(String id, UpdateEventRequest eventRequest) {
        Event eventToUpdate = eventRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        modelMapper.map(eventRequest, eventToUpdate);

        return modelMapper.map(eventToUpdate, EventDTO.class);
    }

    @Transactional
    public EventDTO addArtist(String id, String artistId) {
        Event event = eventRepository.findById(fromString(id)).orElseThrow(ItemNotFoundException::new);

        if(event.getArtists().stream().map(Artist::getArtistId).toList().contains(UUID.fromString(artistId))) {
            throw new IllegalArgumentException(String.format("The artist with id %s is already part of the event %s.", artistId, id));
        }

        Artist artistToAdd = artistService.findById(artistId);

        event.getArtists().add(artistToAdd);

        return modelMapper.map(eventRepository.save(event), EventDTO.class);
    }

    @Transactional
    public void removeArtist(String id, String artistId) {
        Event event = eventRepository.findById(fromString(id)).orElseThrow(ItemNotFoundException::new);

        if(!event.getArtists().stream().map(Artist::getArtistId).toList().contains(fromString(artistId))) {
            throw new IllegalArgumentException(String.format("The artist with id %s is already not part of the event %s.", artistId, id));
        }

        Artist artistToAdd = artistService.findById(artistId);

        event.getArtists().remove(artistToAdd);

        eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(String id) {
        Event eventToDelete = eventRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        eventRepository.delete(eventToDelete);
    }

}