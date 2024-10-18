package com.integracion.backend.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSocialMediaRequest {

    @NotBlank
    private String platform;

    @NotBlank
    private String url;

}