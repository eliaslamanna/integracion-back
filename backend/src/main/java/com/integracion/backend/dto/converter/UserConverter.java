package com.integracion.backend.dto.converter;

import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        try{
            user.setUsername(userDTO.getUsername());
            if(user.getUserId() != null){
                user.setUserId(userDTO.getUserId());
            }
            user.setPassword(userDTO.getPassword());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());

        }
        catch(Exception e){
           throw e;
        }
        return user;
    }

    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        try{
            userDTO.setUsername(user.getUsername());
            userDTO.setUserId(user.getUserId());
            userDTO.setPassword(user.getPassword());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());

        }
        catch(Exception e){
            throw e;
        }
        return userDTO;
    }
}
