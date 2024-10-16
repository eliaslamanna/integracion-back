package com.integracion.backend.controllers;


import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    private ResponseEntity<List<UserDTO>> getUsers() {
        try {
            List<UserDTO> users = userService.getListUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createUser")
    private ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        try{
            userService.addUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
