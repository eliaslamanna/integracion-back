package com.integracion.backend.dto;

import com.integracion.backend.model.Artist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaDTO {

    private UUID id;

    private String platform;

    private String url;

}