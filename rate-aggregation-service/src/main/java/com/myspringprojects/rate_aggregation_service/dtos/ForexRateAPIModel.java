package com.myspringprojects.rate_aggregation_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

// This annotation tells Jackson to ignore any properties in the JSON that don't have a corresponding field in the POJO.
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForexRateAPIModel {

    @JsonProperty("timestamp")
    private long timeUpdated;

    @JsonProperty("base")
    private String baseCurrency;

    @JsonProperty("rates")
    private Map<String, Double> rates;

    // Getters and setters for all fields are essential for Jackson's deserialization
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
        return "ForexRateAPIModel{" +
                "timeUpdated=" + timeUpdated +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", rates=" + rates +
                '}';
    }
}