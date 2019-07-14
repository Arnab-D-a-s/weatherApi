package tech.das.weatherapi.weather.services;

import org.springframework.stereotype.Component;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;
import tech.das.weatherapi.weather.model.TemperatureList;
import tech.das.weatherapi.weather.model.WeatherReportResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class MainProcessDelegator {
    public WeatherReportResponse getForecast(ForecastResponse responseFromApi) throws ExecutionException {
        return (WeatherReportResponse.builder()
                .temperatureLists(getAllLists(responseFromApi))
                .build());
    }

    private List<TemperatureList> getAllLists(ForecastResponse response) {
        List<TemperatureList> outPut = new ArrayList<>();
        response.getListofTemperatures()
                .forEach(l ->
                        outPut.add(
                                TemperatureList.builder()
                                .date(l.getUsableDate())
                                .temperature(l.getTemperatureMasterObject().getCelciusTemp())
                                .build()
                        )
                );
        return outPut;
    }
}
