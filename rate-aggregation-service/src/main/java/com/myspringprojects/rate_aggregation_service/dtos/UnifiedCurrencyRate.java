package com.myspringprojects.rate_aggregation_service.dtos;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UnifiedCurrencyRate {

    private long timeUpdatedUnix;
    private String timeUpdatedUtc;
    private String baseCurrency;
    private String targetCurrency;
    private double conversionRate;

    public UnifiedCurrencyRate(
            long timeUpdatedUnix,
            String timeUpdatedUtc,
            String baseCurrency,
            String targetCurrency,
            double conversionRate) {
        this.timeUpdatedUnix = timeUpdatedUnix;
        this.timeUpdatedUtc = timeUpdatedUtc;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionRate = conversionRate;
    }

    public UnifiedCurrencyRate() {}

    public long getTimeUpdatedUnix() {
        return timeUpdatedUnix;
    }

    public void setTimeUpdatedUnix(long unixTimestamp) {
        this.timeUpdatedUnix = unixTimestamp;
        ZonedDateTime zdt = Instant.ofEpochSecond(unixTimestamp)
                .atZone(ZoneId.of("UTC"));
        this.timeUpdatedUtc = zdt.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    public String getTimeUpdatedUtc() {
        return timeUpdatedUtc;
    }

    public void setTimeUpdatedUtc(String timeUpdatedUtc) {
        this.timeUpdatedUtc = timeUpdatedUtc;
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
}
