
package com.integracion.backend.controllers;


import com.integracion.backend.controllers.request.CreateEventRequest;
import com.integracion.backend.dto.EventDTO;
import com.integracion.backend.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/getEvents")
    public ResponseEntity<List<EventDTO>> getEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable String id) {
        EventDTO event = eventService.getById(id);
        return ok(event);
    }

    @PostMapping("/createEvent")
    public ResponseEntity<EventDTO> createEvent(@RequestBody CreateEventRequest eventRequest) {
        EventDTO createdEvent = eventService.createEvent(eventRequest);
        return new ResponseEntity<>(createdEvent, CREATED);
    }

    @PostMapping("/{id}/addArtist")
    public ResponseEntity<EventDTO> addArtist(@PathVariable String id, @RequestParam String artistId) {
        EventDTO event = eventService.addArtist(id, artistId);
        return ok(event);
    }

    @DeleteMapping("/{id}/removeArtist")
    public ResponseEntity<Void> removeArtist(@PathVariable String id, @RequestParam String artistId) {
        eventService.removeArtist(id, artistId);
        return noContent().build();
    }

}