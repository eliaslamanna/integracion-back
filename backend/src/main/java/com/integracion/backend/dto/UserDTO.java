package com.integracion.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

}