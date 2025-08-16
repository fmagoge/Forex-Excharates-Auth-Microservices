package com.myspringprojects.rate_aggregation_service.components;

import com.myspringprojects.rate_aggregation_service.services.DataAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private DataAggregatorService aggregatorService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running initial data aggregation at startup...");
        aggregatorService.fetchAndAggregateAveragedRates();
        System.out.println("Initial aggregation complete.");
    }
}