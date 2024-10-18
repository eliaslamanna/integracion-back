package com.integracion.backend.controllers;


import com.integracion.backend.controllers.request.CreateSocialMediaRequest;
import com.integracion.backend.controllers.request.UpdateSocialMediaRequest;
import com.integracion.backend.dto.SocialMediaDTO;
import com.integracion.backend.services.SocialMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/socialMedia")
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @GetMapping("/getSocialMedia")
    public ResponseEntity<List<SocialMediaDTO>> getSocialMedia() {
        List<SocialMediaDTO> socialMedias = socialMediaService.getListSocialMedia();
        return ok(socialMedias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialMediaDTO> getById(@PathVariable String id) {
        SocialMediaDTO socialMedia = socialMediaService.getById(id);
        return ok(socialMedia);
    }

    @PostMapping("/createSocialMedia")
    public ResponseEntity<SocialMediaDTO> createSocialMedia(@RequestBody CreateSocialMediaRequest socialMediaRequest) {
        SocialMediaDTO socialMedia = socialMediaService.addSocialMedia(socialMediaRequest);
        return new ResponseEntity<>(socialMedia, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocialMediaDTO> updateSocialMedia(@PathVariable String id, @RequestBody UpdateSocialMediaRequest socialMediaRequest) {
        SocialMediaDTO updatedSocialMedia = socialMediaService.updateSocialMedia(id, socialMediaRequest);
        return ok(updatedSocialMedia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialMedia(@PathVariable String id) {
        socialMediaService.deleteSocialMedia(id);
        return noContent().build();
    }

}