package com.integracion.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap; import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/health")
@RestController class CustomHealthController {

    private final HealthEndpoint healthEndpoint;
    @GetMapping
    public ResponseEntity<Map<String, Object>> customHealthCheck() {
        // Retrieve Actuator health status
        var health = healthEndpoint.health();
        String status = health.getStatus().getCode();

        //Map Actuator response to custom format
        Map<String, Object> response = new HashMap<>();
        if ("UP".equalsIgnoreCase(status)) {
            response.put("ok", true);
            response.put("message", "Service is healthy");
        } else { response.put("ok", false);
            response.put("message", "Service is not healthy");
        }

        // Return custom response
        return ResponseEntity.ok(response); }
    }