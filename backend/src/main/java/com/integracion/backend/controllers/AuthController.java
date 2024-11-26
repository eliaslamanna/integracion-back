package com.integracion.backend.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.gson.GsonFactory;  // Importa GsonFactory
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final String CLIENT_ID = "778783619120-ef3i0vcqb4pdl5d19nmc3lm673hhma7i.apps.googleusercontent.com";

    @PostMapping("/google")
    public String googleLogin(@RequestBody TokenRequest tokenRequest) throws Exception {
        // Usa GsonFactory en lugar de JacksonFactory
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(tokenRequest.getToken());
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
            String email = payload.getEmail();
            boolean emailVerified = Boolean.TRUE.equals(payload.getEmailVerified());

            // Procesa los datos del usuario
            return "Login exitoso: " + email;
        } else {
            throw new Exception("Invalid ID token.");
        }
    }

    @Setter
    @Getter
    public static class TokenRequest {
        private String token;

    }
}
