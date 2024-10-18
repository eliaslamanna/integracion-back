package com.integracion.backend.controllers;


import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getListUsers();
        return ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
        UserDTO user = userService.getById(id);
        return ok(user);
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, CREATED);
    }

}