package com.integracion.backend.services;

import com.integracion.backend.controllers.request.CreateUserRequest;
import com.integracion.backend.controllers.request.UpdateUserRequest;
import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.User;
import com.integracion.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<UserDTO> getListUsers() {
        return userRepository.findAll() //hasta aca es jpa. las lineas siguientes es java puro
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class)) // es similar a: user -> userConverter.convertUserToUserDTO(user)
                .toList();
    }

    @Transactional
    public UserDTO getById(String id) {
        var user = userRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional
    public UserDTO addUser(CreateUserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Transactional
    public UserDTO updateUser(String id, UpdateUserRequest userRequest) {
        User userToUpdate = userRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        modelMapper.map(userRequest, userToUpdate);
        return modelMapper.map(userRepository.save(userToUpdate), UserDTO.class);
    }

    @Transactional
    public void deleteUser(String id) {
        User userToDelete = userRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        userRepository.delete(userToDelete);
    }

}