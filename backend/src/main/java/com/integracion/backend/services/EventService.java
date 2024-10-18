package com.integracion.backend.services;

import com.integracion.backend.controllers.request.CreateEventRequest;
import com.integracion.backend.dto.EventDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.Artist;
import com.integracion.backend.model.Event;
import com.integracion.backend.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.UUID.fromString;

@Service
@AllArgsConstructor
public class EventService {

    private final ArtistService artistService;

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(event -> modelMapper.map(event, EventDTO.class)).toList();
    }

    @Transactional
    public EventDTO getById(String id) {
        var event = eventRepository.findById(fromString(id)).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(event, EventDTO.class);
    }


    @Transactional
    public EventDTO createEvent(CreateEventRequest eventRequest) {
        Event event = modelMapper.map(eventRequest, Event.class);

        if(!eventRequest.getArtistIds().isEmpty()) {
            List<Artist> artists = artistService.getAllByIdIn(eventRequest.getArtistIds());
            event.setArtists(artists);
        }

        return modelMapper.map(eventRepository.save(event), EventDTO.class);
    }

    @Transactional
    public EventDTO addArtist(String id, String artistId) {
        Event event = eventRepository.findById(fromString(id)).orElseThrow(ItemNotFoundException::new);

        if(event.getArtists().stream().map(Artist::getArtistId).toList().contains(artistId)) {
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

}