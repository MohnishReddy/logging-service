package com.example.loggingservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping
@ResponseBody
public class HealthCheckController {
    @CrossOrigin
    @GetMapping("/health")
    public String healthCheck() {
        return "Ping!";
    }
}
