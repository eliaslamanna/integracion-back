package com.integracion.backend.services;

import com.integracion.backend.controllers.request.CreateEventRequest;
import com.integracion.backend.dto.EventDTO;
import com.integracion.backend.model.Artist;
import com.integracion.backend.model.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventConverter {

    private final ModelMapper modelMapper;

    public EventDTO mapToDTO(Event event) {
        EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
        eventDTO.setArtistNames(event.getArtists().stream().map(Artist::getArtisticName).toList());

        return eventDTO;
    }

    public Event mapToEvent(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }

    public Event mapToEvent(CreateEventRequest eventRequest) {
        return modelMapper.map(eventRequest, Event.class);
    }

}
