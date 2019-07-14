package tech.das.weatherapi.weather.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.das.weatherapi.restClient.ClientConfig;
import tech.das.weatherapi.weather.datamodels.OpenWeatherRequestor;
import tech.das.weatherapi.weather.datamodels.WeatherReportResponse;
import tech.das.weatherapi.weather.services.MainProcessDelegator;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "Weather API", description = "To get Weather reports", tags = "Weather API")
public class WeatherProductController {

    private final ClientConfig clientApi;
    private final MainProcessDelegator mainProcessDelegator;

    @RequestMapping(value = "/futureWeather", method = RequestMethod.GET)
    @GetMapping(path = "weather-api", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get weather report for this loctaion for the next 5 days", response = WeatherReportResponse.class, nickname = "getWeatherReport")
    public ResponseEntity<WeatherReportResponse> getWeatherReport(@ApiParam(value = "LocationID", example = "2618425")
                                                                  @RequestParam(value = "LocationID", defaultValue = "2618425") final Integer locationCode
    ) throws InterruptedException, ExecutionException {
        WeatherReportResponse response =  mainProcessDelegator.getForecast(clientApi.getForecastResponseFromApi(OpenWeatherRequestor.builder()
                .location(String.valueOf(locationCode))
                .countryCode("dummy")
                .build()));
        return ResponseEntity.ok(response);
    }
}
