package com.myspringprojects.apigateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/currency-service-fallback")
    public String ratesServiceFallBack() {
        return "Rates Service is taking longer than expected. Please try again later.";
    }

    @GetMapping("/auth-service-fallback")
    public String authServiceFallBack() {
        return "Auth Service is taking longer than expected. Please try again later.";
    }
}
