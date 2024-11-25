package com.integracion.backend.repository;

import com.integracion.backend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
}