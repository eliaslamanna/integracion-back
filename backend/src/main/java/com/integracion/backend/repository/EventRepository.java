package com.integracion.backend.repository;

import com.integracion.backend.model.Artist;
import com.integracion.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}