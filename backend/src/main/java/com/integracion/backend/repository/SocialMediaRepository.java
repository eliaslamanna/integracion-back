package com.integracion.backend.repository;

import com.integracion.backend.model.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, UUID> {
}