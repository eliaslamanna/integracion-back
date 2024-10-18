package com.integracion.backend.controllers;


import com.integracion.backend.controllers.request.CreateUserRequest;
import com.integracion.backend.controllers.request.UpdateUserRequest;
import com.integracion.backend.dto.UserDTO;
import com.integracion.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest userRequest) {
        UserDTO createdUser = userService.addUser(userRequest);
        return new ResponseEntity<>(createdUser, CREATED);
    }

    @PutMapping("/{id}/updateUser")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest userRequest) {
        UserDTO updatedUser = userService.updateUser(id, userRequest);
        return ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return noContent().build();
    }

}