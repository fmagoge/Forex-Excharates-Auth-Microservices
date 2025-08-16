package com.myspringprojects.rate_aggregation_service.repository;

import com.myspringprojects.rate_aggregation_service.model.AverageCurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AverageCurrencyRateRepository extends JpaRepository<AverageCurrencyRate, Long> {
}
