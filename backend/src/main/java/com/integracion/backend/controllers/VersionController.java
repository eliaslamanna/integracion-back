package com.integracion.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {

        @RequestMapping("")
        public String getVersion() {
            return "1.0.1";
        }
}
