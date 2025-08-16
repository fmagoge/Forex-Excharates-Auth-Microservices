package com.myspringprojects.rate_aggregation_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

// The @JsonIgnoreProperties annotation prevents Jackson from throwing an exception if the JSON contains fields not present in the POJO.
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreeCurrencyAPIModel {

    @JsonProperty("timestamp")
    private long timeUpdated;

    @JsonProperty("base")
    private String baseCurrency;

    @JsonProperty("rates")
    private Map<String, Double> rates;

    // Getters and setters are necessary for Jackson to populate the POJO.
    public long getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(long timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "FreeCurrencyAPIModel{" +
                "timeUpdated=" + timeUpdated +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", rates=" + rates +
                '}';
    }
}