package com.myspringprojects.rate_aggregation_service.services;

import com.myspringprojects.rate_aggregation_service.model.AverageCurrencyRate;
import com.myspringprojects.rate_aggregation_service.repository.AverageCurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyDataService {

    @Autowired
    private AverageCurrencyRateRepository averageCurrencyRateRepository;

    public void saveAverageCurrencyRate(List<AverageCurrencyRate> averageRates) {
        try {
            if (averageRates != null && !averageRates.isEmpty()) {
                averageCurrencyRateRepository.saveAll(averageRates);
                System.out.println("Average currency rates saved successfully.");
            } else {
                System.out.println("No average rates to save.");
            }
        } catch (Exception e) {
            System.err.println("Error saving average currency rates: " + e.getMessage());
        }
    }

    public List<AverageCurrencyRate> getAllAverageCurrencyRates() {
        try {
            return averageCurrencyRateRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error retrieving average currency rates: " + e.getMessage());
            return List.of(); // Return an empty list in case of error
        }
    }

}