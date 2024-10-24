package com.integracion.backend.controllers;


import com.integracion.backend.controllers.request.CreateImageRequest;
import com.integracion.backend.controllers.request.UpdateImageRequest;
import com.integracion.backend.dto.ImageDTO;
import com.integracion.backend.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/getImages")
    public ResponseEntity<List<ImageDTO>> getImages() {
        List<ImageDTO> images = imageService.getListImages();
        return ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> getById(@PathVariable String id) {
        ImageDTO image = imageService.getById(id);
        return ok(image);
    }

    @GetMapping
    public ResponseEntity<ImageDTO> getByUrl(@RequestParam String url) {
        ImageDTO image = imageService.getByUrl(url);
        return ok(image);
    }

    @PostMapping("/createImage")
    public ResponseEntity<ImageDTO> createImage(@RequestBody CreateImageRequest imageRequest) {
        ImageDTO image = imageService.addImage(imageRequest);
        return new ResponseEntity<>(image, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDTO> updateImage(@PathVariable String id, @RequestBody UpdateImageRequest imageRequest) {
        ImageDTO updatedImage = imageService.updateImage(id, imageRequest);
        return ok(updatedImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable String id) {
        imageService.deleteImage(id);
        return noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteImageByUrl(@RequestParam String url) {
        imageService.deleteImageByUrl(url);
        return noContent().build();
    }

}