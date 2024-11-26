package com.integracion.backend.repository;

import com.integracion.backend.model.Event;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByFechaBetween(LocalDate fechaAfter, LocalDate fechaBefore, Limit limit);

}