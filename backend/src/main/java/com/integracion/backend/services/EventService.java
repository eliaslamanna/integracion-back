package com.integracion.backend.services;

import com.integracion.backend.controllers.request.ArtistSearchParametersRequest;
import com.integracion.backend.dto.ArtistDTO;
import com.integracion.backend.dto.EventDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.Artist;
import com.integracion.backend.model.Event;
import com.integracion.backend.repository.ArtistRepository;
import com.integracion.backend.repository.EventRepository;
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
public class EventService {

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
        var event = eventRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(event, EventDTO.class);
    }


    @Transactional
    public EventDTO addArtist(EventDTO eventDTO) {
        Event event = modelMapper.map(eventDTO, Event.class);
        return modelMapper.map(eventRepository.save(event), EventDTO.class);
    }

}
