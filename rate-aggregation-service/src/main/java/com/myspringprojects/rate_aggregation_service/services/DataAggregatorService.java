package com.myspringprojects.rate_aggregation_service.services;


import com.myspringprojects.rate_aggregation_service.dtos.ExchangeRateAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.ForexRateAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.FreeCurrencyAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.UnifiedCurrencyRate;
import com.myspringprojects.rate_aggregation_service.mappers.UnifiedToAverageRateMapper;
import com.myspringprojects.rate_aggregation_service.model.AverageCurrencyRate;
import com.myspringprojects.rate_aggregation_service.util.CurrencyRateAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DataAggregatorService {

    @Autowired
    private CurrencyApiClient apiClient;

    @Autowired
    private CurrencyRateAggregator aggregator;

    @Autowired
    private CurrencyDataService currencyDataService;

    public List<UnifiedCurrencyRate> fetchAndAggregateAveragedRates() throws Exception {

        CompletableFuture<ExchangeRateAPIModel> future1 = apiClient.getExchangeRateApiData();
        CompletableFuture<ForexRateAPIModel> future2 = apiClient.getAllRatesForexRateApiData();
        CompletableFuture<FreeCurrencyAPIModel> future3 = apiClient.getFreeCurrencyApiData();

        CompletableFuture.allOf(future1, future2, future3).join();

        // Retrieve the results from the completed futures
        ExchangeRateAPIModel model1 = future1.get();
        ForexRateAPIModel model2 = future2.get();
        FreeCurrencyAPIModel model3 = future3.get();

        List<UnifiedCurrencyRate> unifiedRates = aggregator.aggregateAndAverage(model1, model2, model3);
        List<AverageCurrencyRate> averageRates = new ArrayList<>();
        for (UnifiedCurrencyRate unifiedCurrencyRate: unifiedRates) {
            UnifiedToAverageRateMapper mapper = new UnifiedToAverageRateMapper();
            mapper.mapToEntity(unifiedCurrencyRate);
            averageRates.add(mapper.mapToEntity(unifiedCurrencyRate));
        }

        currencyDataService.saveAverageCurrencyRate(averageRates);



        // Pass the data to your aggregation and averaging logic
        return aggregator.aggregateAndAverage(model1, model2, model3);
    }
}