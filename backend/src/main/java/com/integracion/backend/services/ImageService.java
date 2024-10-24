package com.integracion.backend.services;

import com.integracion.backend.controllers.request.CreateImageRequest;
import com.integracion.backend.controllers.request.UpdateImageRequest;
import com.integracion.backend.dto.ImageDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.Image;
import com.integracion.backend.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<ImageDTO> getListImages() {
        return imageRepository.findAll()
                .stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .toList();
    }

    @Transactional
    public ImageDTO getById(String id) {
        var socialMedia = imageRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(socialMedia, ImageDTO.class);
    }

    @Transactional
    public ImageDTO getByUrl(String url) {
        var socialMedia = imageRepository.findByUrl(url).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(socialMedia, ImageDTO.class);
    }

    @Transactional
    public List<Image> getAllByIdIn(List<String> ids) {
        return imageRepository.findAllById(ids.stream().map(UUID::fromString).toList());
    }

    @Transactional
    public List<Image> getAllByUrl(List<String> urls) {
        return imageRepository.findAllByUrlIn(urls);
    }

    @Transactional
    public ImageDTO addImage(CreateImageRequest imageRequest) {
        var image = modelMapper.map(imageRequest, Image.class);
        return modelMapper.map(imageRepository.save(image), ImageDTO.class);
    }

    @Transactional
    public List<Image> saveAll(List<Image> imagesToSave) {
        return imageRepository.saveAll(imagesToSave);
    }

    @Transactional
    public ImageDTO updateImage(String id, UpdateImageRequest imageRequest) {
        Image imageToUpdate = imageRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        modelMapper.map(imageRequest, imageToUpdate);
        return modelMapper.map(imageToUpdate, ImageDTO.class);
    }

    @Transactional
    public void deleteImage(String id) {
        Image imageToDelete = imageRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        imageRepository.delete(imageToDelete);
    }

    @Transactional
    public void deleteImageByUrl(String url) {
        Image imageToDelete = imageRepository.findByUrl(url).orElseThrow(ItemNotFoundException::new);
        imageRepository.delete(imageToDelete);
    }

}