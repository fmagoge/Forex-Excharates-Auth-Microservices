package com.myspringprojects.rate_aggregation_service.dtos;

public class ExchangeRateAPIModel {

    private String timeUpdated;
    private String baseCurrency;
    private String targetCurrency;
    private double conversionRate;

    // Default constructor is often required for JSON libraries
    public ExchangeRateAPIModel() {
    }

    // Getters and Setters for each field
    // (This is standard practice for POJOs and allows the library to inject data)
    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public String toString() {
        return "ExchangeRateAPIModel{" +
                "timeUpdated='" + timeUpdated + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", conversionRate=" + conversionRate +
                '}';
    }
}