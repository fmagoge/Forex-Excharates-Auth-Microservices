package com.myspringprojects.rate_aggregation_service.util;


import com.myspringprojects.rate_aggregation_service.dtos.ExchangeRateAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.ForexRateAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.FreeCurrencyAPIModel;
import com.myspringprojects.rate_aggregation_service.dtos.UnifiedCurrencyRate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyRateAggregator {

    public List<UnifiedCurrencyRate> aggregateAndAverage(
            ExchangeRateAPIModel model1,
            ForexRateAPIModel model2,
            FreeCurrencyAPIModel model3) {


        Map<String, List<Double>> ratesByCurrency = new HashMap<>();

        class RateAdder {
            void addRate(String target, Double rate) {
                ratesByCurrency.computeIfAbsent(target, k -> new ArrayList<>()).add(rate);
            }
        }
        RateAdder rateAdder = new RateAdder();

        if (model1 != null) {
            rateAdder.addRate(model1.getTargetCurrency(), model1.getConversionRate());
        }

        if (model2 != null && model2.getRates() != null) {
            model2.getRates().forEach(rateAdder::addRate);
        }

        if (model3 != null && model3.getRates() != null) {
            model3.getRates().forEach(rateAdder::addRate);
        }

        List<UnifiedCurrencyRate> unifiedRates = new ArrayList<>();
        long currentTimestamp = Instant.now().getEpochSecond();

        ratesByCurrency.forEach((targetCurrency, ratesList) -> {

            double averageRate = ratesList.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            UnifiedCurrencyRate unifiedRate = new UnifiedCurrencyRate();
            unifiedRate.setTimeUpdatedUnix(currentTimestamp);

            unifiedRate.setBaseCurrency(model1 != null ? model1.getBaseCurrency() : "N/A");
            unifiedRate.setTargetCurrency(targetCurrency);
            unifiedRate.setConversionRate(averageRate);

            unifiedRates.add(unifiedRate);
        });

        return unifiedRates;
    }


}