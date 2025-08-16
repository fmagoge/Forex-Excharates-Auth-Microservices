package com.myspringprojects.rate_aggregation_service.components;

import com.myspringprojects.rate_aggregation_service.services.DataAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledRateFetcher {

    @Autowired
    private DataAggregatorService aggregatorService;

    @Scheduled(fixedRateString = "${app.scheduling.rate-fetch-interval-ms}")
    public void fetchAndAggregateHourly() {
        System.out.println("Running hourly data aggregation...");
        try {
            aggregatorService.fetchAndAggregateAveragedRates();
            System.out.println("Hourly aggregation complete.");
        } catch (Exception e) {
            System.err.println("Hourly aggregation failed: " + e.getMessage());
        }
    }
}