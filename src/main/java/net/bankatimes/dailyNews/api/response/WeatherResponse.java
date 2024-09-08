package net.bankatimes.dailyNews.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class WeatherResponse {
    @JsonProperty("WeatherText")
    private String weatherText;
    @JsonProperty("Temperature")
    private Temperature temperature;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Temperature{
        @JsonProperty("Metric")
        private Metric metric;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metric{
        @JsonProperty("Value")
        private float metricValue;
        @JsonProperty("Unit")
        private String metricUnit;
    }
}
