package com.myspringprojects.rate_aggregation_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "average_exchange_rate")
public class AverageCurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long timeUpdatedUnix;

    @Column(name = "calculation_time", nullable = true)
    private String timeUpdatedUtc;

    @Column(name = "base_currency", nullable = true, length = 3)
    private String baseCurrency;

    @Column(name = "target_currency", nullable = true, length = 3)
    private String targetCurrency;

    @Column(name = "average_rate", nullable = true, precision = 19, scale = 6)
    private BigDecimal conversionRate;

    @Column(name = "marked_up_rate", nullable = true, precision = 19, scale = 6)
    private BigDecimal markedUpRate;

    public AverageCurrencyRate() {}

    public AverageCurrencyRate(
            long timeUpdatedUnix,
            String timeUpdatedUtc,
            String baseCurrency,
            String targetCurrency,
            BigDecimal conversionRate,
            BigDecimal markedUpRate) {
        this.timeUpdatedUnix = timeUpdatedUnix;
        this.timeUpdatedUtc = timeUpdatedUtc;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionRate = conversionRate;
        this.markedUpRate = markedUpRate;
    }

    public long getTimeUpdatedUnix() {
        return timeUpdatedUnix;
    }

    public void setTimeUpdatedUnix(long timeUpdatedUnix) {
        this.timeUpdatedUnix = timeUpdatedUnix;
    }

    public String getTimeUpdatedUtc() {
        return timeUpdatedUtc;
    }

    public void setTimeUpdatedUtc(String timeUpdatedUtc) {
        this.timeUpdatedUtc = timeUpdatedUtc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public BigDecimal getMarkedUpRate() {
        return markedUpRate;
    }

    public void setMarkedUpRate(BigDecimal markedUpRate) {
        this.markedUpRate = markedUpRate;
    }
}
