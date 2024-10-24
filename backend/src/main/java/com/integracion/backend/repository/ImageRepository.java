package com.integracion.backend.repository;

import com.integracion.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {

    Optional<Image> findByUrl(String url);

    List<Image> findAllByUrlIn(List<String> urls);

}
