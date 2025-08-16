package com.myspringprojects.rate_aggregation_service.services;


import com.myspringprojects.rate_aggregation_service.dtos.ExchangeRateAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.ForexRateAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.FreeCurrencyAPIModel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class CurrencyApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<ExchangeRateAPIModel> getExchangeRateApiData() {
        String apiUrl = "https://v6.exchangerate-api.com/v6/b5a2352c9114294cba5251e8/pair/USD/GBP";
        try {
            ExchangeRateAPIModel response = restTemplate.getForObject(apiUrl, ExchangeRateAPIModel.class);
            return CompletableFuture.completedFuture(response);
        } catch (RestClientException e) {
            System.err.println("Error fetching data from API 1: " + e.getMessage());
            return CompletableFuture.completedFuture(null);
        }
    }

    @Async
    public CompletableFuture<ForexRateAPIModel> getAllRatesForexRateApiData() {
        String apiUrl = "https://api.forexrateapi.com/v1/latest?api_key=d5696d818464c1bf3f668516e54e1a58&base=USD&currencies=GBP,ZAR";
        try {
            ForexRateAPIModel response = restTemplate.getForObject(apiUrl, ForexRateAPIModel.class);
            return CompletableFuture.completedFuture(response);
        } catch (RestClientException e) {
            System.err.println("Error fetching data from API 2: " + e.getMessage());
            return CompletableFuture.completedFuture(null);
        }
    }

    @Async
    public CompletableFuture<FreeCurrencyAPIModel> getFreeCurrencyApiData() {
        String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_hPiAOXn2Sqosa9Ks9S5ZjO8U6A10ctjNLle3vkF1&currencies=GBP%2CZAR";
        try {
            FreeCurrencyAPIModel response = restTemplate.getForObject(apiUrl, FreeCurrencyAPIModel.class);
            return CompletableFuture.completedFuture(response);
        } catch (RestClientException e) {
            System.err.println("Error fetching data from API 3: " + e.getMessage());
            return CompletableFuture.completedFuture(null);
        }
    }
}