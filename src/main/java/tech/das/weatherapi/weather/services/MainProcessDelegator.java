package tech.das.weatherapi.weather.services;

import org.springframework.stereotype.Component;
import tech.das.weatherapi.weather.model.WeatherReportResponse;

import java.util.concurrent.ExecutionException;

@Component
public class MainProcessDelegator {
    public WeatherReportResponse getWeather (String locations, String temperature) throws ExecutionException {
        return (WeatherReportResponse.builder().dummy("new").build());
    }
}
