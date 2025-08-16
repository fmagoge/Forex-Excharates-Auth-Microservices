package com.myspringprojects.rate_aggregation_service.mappers;

import com.myspringprojects.rate_aggregation_service.dtos.UnifiedCurrencyRate;
import com.myspringprojects.rate_aggregation_service.model.AverageCurrencyRate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UnifiedToAverageRateMapper {

    public AverageCurrencyRate mapToEntity(UnifiedCurrencyRate dto) {
        if (dto == null) {
            return null;
        }

        AverageCurrencyRate entity = new AverageCurrencyRate();

        // Map basic fields directly
        entity.setTimeUpdatedUnix(dto.getTimeUpdatedUnix());
        entity.setTimeUpdatedUtc(dto.getTimeUpdatedUtc());
        entity.setBaseCurrency(dto.getBaseCurrency());
        entity.setTargetCurrency(dto.getTargetCurrency());

        // Map and convert the double to BigDecimal
        BigDecimal conversionRate = BigDecimal.valueOf(dto.getConversionRate());
        entity.setConversionRate(conversionRate);

        // Calculate a simple markup (e.g., 5%) for the markedUpRate
        BigDecimal markupPercentage = new BigDecimal("0.05");
        BigDecimal markedUpRate = conversionRate.add(conversionRate.multiply(markupPercentage));
        entity.setMarkedUpRate(markedUpRate);

        return entity;
    }
}