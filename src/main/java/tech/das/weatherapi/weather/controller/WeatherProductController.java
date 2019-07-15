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
import tech.das.weatherapi.weather.datamodels.UnitType;
import tech.das.weatherapi.weather.datamodels.WeatherReportResponse;
import tech.das.weatherapi.weather.datamodels.WeatherSummaryResponse;
import tech.das.weatherapi.weather.services.MainProcessDelegator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "Weather API", description = "To get Weather reports", tags = "Weather API")
public class WeatherProductController {

    private final ClientConfig clientApi;
    private final MainProcessDelegator mainProcessDelegator;

    @RequestMapping(value = "/weather/locations", method = RequestMethod.GET)
    @GetMapping(path = "weather-api", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get weather report for this loctaion for the next 5 days", response = WeatherReportResponse.class, nickname = "getWeatherReport")
    public ResponseEntity<WeatherReportResponse> getWeatherForecast(@ApiParam(value = "LocationID", example = "2618425")
                                                                    @RequestParam(value = "LocationID", defaultValue = "2618425") final Integer locationCode,
                                                                    @ApiParam(value = "unit", example = "Celcius", required = false)
                                                                    @RequestParam(value = "unit", required = false) final String unit
    ) throws InterruptedException, ExecutionException {
        WeatherReportResponse response = mainProcessDelegator.getForecast(locationCode, unit);
        if (response.getCode() != null) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.ok(response);

    }

    @RequestMapping(value = "/weather/summary", method = RequestMethod.GET)
    @GetMapping(path = "weather-api", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Check these places for travelling tomorrow", response = WeatherSummaryResponse.class, nickname = "getWeatherReport")
    public ResponseEntity<WeatherSummaryResponse> getWeatherSummary(@ApiParam(value = "LocationIDs separated by comma", example = "2618425,4164138,1275004")
                                                                    @RequestParam(value = "LocationIDs separated by comma", defaultValue = "2618425,4164138,1275004") final String LocationIds,
                                                                    @ApiParam(value = "Units", example = "CELSIUS")
                                                                    @RequestParam(value = "Units", defaultValue = "CELSIUS") final String unit,
                                                                    @ApiParam(value = "Temperature Threshold", example = "20")
                                                                    @RequestParam(value = "Temperature Threshold", defaultValue = "20") final Integer temperature
    ) throws InterruptedException, ExecutionException, NumberFormatException {

        List<Integer> locationList = new ArrayList<>();
        try {
            String[] values = LocationIds.split("\\s*,\\s*");
            List<String> demo = Arrays.asList(values);
            for (String d : demo) {
                locationList.add(Integer.valueOf(d));
            }
        }
        catch (NumberFormatException e) {
            return ResponseEntity.ok().body((WeatherSummaryResponse.builder()
                    .message("The Location Code must be a number " + e.getMessage())
                    .build()));
        }

        UnitType unitType;
        switch (unit.toUpperCase()) {
            case ("CELSIUS"):
                unitType = UnitType.METRIC;
                break;
            case ("FAHRENHEIT"):
                unitType = UnitType.IMPERIAL;
                break;
            default:
                unitType = UnitType.METRIC;
                break;
        }

        WeatherSummaryResponse response = mainProcessDelegator.getWeatherSummary(locationList, unitType, BigDecimal.valueOf(temperature));
        return ResponseEntity.ok(response);
    }

/*    private List<Integer> stringToIntList(String commasepLocations) throws NumberFormatException {
        List<Integer> op = new ArrayList<>();
        String[] values = commasepLocations.split("\\s*,\\s*");
        List<String> demo = Arrays.asList(values);
        for (String d : demo) {
            op.add(Integer.valueOf(d));
        }
        return op;
    }*/
}
