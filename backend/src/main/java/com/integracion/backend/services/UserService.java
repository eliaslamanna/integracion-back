package com.integracion.backend.services;

import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.dto.converter.UserConverter;
import com.integracion.backend.model.User;
import com.integracion.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;

    }

    @Transactional
    public List<UserDTO> getListUsers() {
        try{
            return userRepository.findAll() //hasta aca es jpa. las lineas siguientes es java puro
                    .stream()
                    .map(userConverter::convertUserToUserDTO) // es similar a: user -> userConverter.convertUserToUserDTO(user)
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            throw e;
        }

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
