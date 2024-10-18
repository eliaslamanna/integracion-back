package com.integracion.backend.services;

import com.integracion.backend.controllers.request.CreateSocialMediaRequest;
import com.integracion.backend.dto.SocialMediaDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.SocialMedia;
import com.integracion.backend.repository.SocialMediaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<SocialMediaDTO> getListSocialMedia() {
        return socialMediaRepository.findAll()
                .stream()
                .map(socialMedia -> modelMapper.map(socialMedia, SocialMediaDTO.class))
                .toList();
    }

    @Transactional
    public SocialMediaDTO getById(String id) {
        var socialMedia = socialMediaRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(socialMedia, SocialMediaDTO.class);
    }

    @Transactional
    public List<SocialMedia> getAllByIdIn(List<String> ids) {
        return socialMediaRepository.findAllById(ids.stream().map(UUID::fromString).toList());
    }

    @Transactional
    public SocialMediaDTO addSocialMedia(CreateSocialMediaRequest socialMediaRequest) {
        var socialMedia = modelMapper.map(socialMediaRequest, SocialMedia.class);
        return modelMapper.map(socialMediaRepository.save(socialMedia), SocialMediaDTO.class);
    }

}