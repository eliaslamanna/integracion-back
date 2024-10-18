package com.integracion.backend.repository;

import com.integracion.backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
}