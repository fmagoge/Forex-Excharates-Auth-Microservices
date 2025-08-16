package com.myspringprojects.rate_aggregation_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfigProperties {
    private Scheduling scheduling;
    private Api api;

    // Getters and Setters
    public static class Scheduling {
        private long rateFetchIntervalMs;

        public long getRateFetchIntervalMs() {
            return rateFetchIntervalMs;
        }

        public void setRateFetchIntervalMs(long rateFetchIntervalMs) {
            this.rateFetchIntervalMs = rateFetchIntervalMs;
        }
    }

    public static class Api {
        private ExchangeRate exchangeRate;
        private ForexRate forexRate;
        private FreeCurrency freeCurrency;

        // Getters and Setters
        public static class ExchangeRate {
            private String key;
            private String url;

            // Getters and Setters
        }

        public static class ForexRate {
            private String key;
            private String url;

            // Getters and Setters
        }

        public static class FreeCurrency {
            private String key;
            private String url;

            // Getters and Setters
        }
    }
}