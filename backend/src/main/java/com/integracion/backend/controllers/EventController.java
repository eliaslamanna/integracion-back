
package com.integracion.backend.controllers;


import com.integracion.backend.dto.EventDTO;
import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.services.EventService;
import com.integracion.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/getEvents")
    public ResponseEntity<List<EventDTO>> getEvents() {
        try {
            List<EventDTO> events = eventService.getAllEvents();
            return ok(events);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable String id) {
        try {
            EventDTO event = eventService.getById(id);

            return ok(event);
        }
        catch (ItemNotFoundException ie) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @PostMapping("/createEvent")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO event) {
        try{
            eventService.addArtist(event);
            return new ResponseEntity<>(event, CREATED);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

}