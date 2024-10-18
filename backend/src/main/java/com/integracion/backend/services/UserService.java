package com.integracion.backend.services;

import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.dto.converter.UserConverter;
import com.integracion.backend.exception.ItemNotFoundException;
import com.integracion.backend.model.User;
import com.integracion.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Transactional
    public List<UserDTO> getListUsers() {
        try{
            return userRepository.findAll() //hasta aca es jpa. las lineas siguientes es java puro
                    .stream()
                    .map(userConverter::convertUserToUserDTO) // es similar a: user -> userConverter.convertUserToUserDTO(user)
                    .toList();
        }
        catch(Exception e){
            throw e;
        }
    }

    @Transactional
    public UserDTO getById(String id) {
        var user = userRepository.findById(UUID.fromString(id)).orElseThrow(ItemNotFoundException::new);
        return userConverter.convertUserToUserDTO(user);
    }

    @Transactional
    public void addUser(UserDTO userDTO) {
        try{
            User user = userConverter.convertUserDTOToUser(userDTO);
            userRepository.save(user);
        }
        catch(Exception e){
            throw e;
        }
    }
}
