package tech.das.weatherapi.weather.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.das.weatherapi.weather.model.WeatherReportResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "Weather API", description = "To get Weather reports", tags = "Weather API")
public class WeatherProductController {

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    @GetMapping(path = "weather-api", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get weather report", response = WeatherReportResponse.class, nickname = "getWeatherReport")
    public ResponseEntity<WeatherReportResponse> getWeatherReport(@ApiParam(value = "Location", example = "Copenhagen")
                                                               @RequestParam(value = "Location", defaultValue = "Copenhagen") final String locations,
                                                               @ApiParam(value = "Temperature", example = "10")
                                                               @RequestParam(value = "Temperature", defaultValue = "10" ) final int temperature) throws InterruptedException {
        WeatherReportResponse reposne = new WeatherReportResponse();
        log.info("Hey I got it");
        reposne.setDummy(locations.concat("Hi"));
        return ResponseEntity.ok(reposne);
    }
}
