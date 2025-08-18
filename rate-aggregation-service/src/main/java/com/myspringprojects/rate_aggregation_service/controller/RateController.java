package com.myspringprojects.rate_aggregation_service.controller;


import com.myspringprojects.rate_aggregation_service.services.CurrencyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rates")
public class RateController {

    @Autowired
    private CurrencyDataService currencyDataService;

    @GetMapping("/average-currency-rates")
    public ResponseEntity<?> getAverageCurrencyRates() {
        try {
            return ResponseEntity.ok(currencyDataService.getAllAverageCurrencyRates());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving average currency rates: " + e.getMessage());
        }
    }

}