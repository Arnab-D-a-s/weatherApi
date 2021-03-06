package tech.das.weatherapi.weather.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.das.weatherapi.restclient.ClientConfig;
import tech.das.weatherapi.restclient.DTO.ForecastResponse;
import tech.das.weatherapi.restclient.DTO.TempListFromApi;
import tech.das.weatherapi.weather.datamodels.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class MainProcessDelegator {

    private final ClientConfig apiClient;

    public WeatherReportResponse getForecast(Integer location, String unit) throws ExecutionException {
        ForecastResponse responseFromApi = apiClient.getForecastResponseFromApi(String.valueOf(location));
        if (responseFromApi.getCode().compareTo(200) != 0) {
            return WeatherReportResponse.builder().message(responseFromApi.getMessage()).code(responseFromApi.getCode()).build();
        }
        UnitType unitType = UnitType.METRIC;
        if (unit != null) {
            if (unit.toUpperCase().equals("FAHRENHEIT")) {
                unitType = UnitType.IMPERIAL;
            }
        }
        return (WeatherReportResponse.builder()
                .temperatureLists(getAllLists(responseFromApi, unitType))
                .build());
    }

    private List<TemperatureList> getAllLists(ForecastResponse response, UnitType unitType) {
        List<TemperatureList> outPut = new ArrayList<>();
        response.getListofTemperatures()
                .forEach(l ->
                        outPut.add(
                                TemperatureList.builder()
                                        .date(l.getUsableDate())
                                        .temperature(
                                                (unitType.compareTo(UnitType.METRIC) == 0) ?
                                                        l.getTemperatureMasterObject().getCelciusTemp() :
                                                        l.getTemperatureMasterObject().getFahrenheitTemp())
                                        .build()
                        )
                );
        return outPut;
    }

    public WeatherSummaryResponse getWeatherSummary(List<Integer> locationsToSearch, UnitType unit, BigDecimal temp) {
        List<FavCities> favCities = new ArrayList<>();
        List<ForecastResponse> forecastResponses = new ArrayList<>();
        locationsToSearch.forEach(
                l -> forecastResponses
                        .add(apiClient
                                .getForecastResponseFromApi(String.valueOf(l))));
        Optional<ForecastResponse> errorElement = forecastResponses
                .stream()
                .filter(forecastResponse -> forecastResponse.getCode().compareTo(200) != 0)
                .findFirst();
        if (errorElement.isPresent()) {
            return WeatherSummaryResponse.builder()
                    .message("Error from Server" + errorElement.get().getMessage())
                    .build();
        }

        forecastResponses
                .forEach(l -> {
                    Predicate<TempListFromApi> tempCompare;
                    if (unit.compareTo(UnitType.METRIC) == 0) {
                        tempCompare = p -> p.getTemperatureMasterObject().getCelciusTemp().compareTo(temp) > 0;
                    } else {
                        tempCompare = p -> p.getTemperatureMasterObject().getFahrenheitTemp().compareTo(temp) > 0;
                    }
                    Optional<TempListFromApi> tempListForTomorrow = l.getListofTemperatures()
                            .stream()
                            .filter(tempListFromApi -> tempListFromApi
                                    .getLocalDate().isEqual(LocalDate.now().plusDays(1)))
                            .filter(tempCompare)
                            .findFirst();
                    tempListForTomorrow.ifPresent(t -> favCities.add(FavCities.builder()
                            .city(l.getCityDetails().getCityName())
                            .temperature(t.getTemperatureMasterObject().getCelciusTemp())
                            .locationCode(l.getCityDetails().getCityCode())
                            .build()));
                });
        if (favCities.size() == 0) {
            favCities.add(FavCities.builder().city("No location in the choice is warmer than this. " +
                    "Try adding Some more tropical cities")
                    .build());
        }
        return (WeatherSummaryResponse.builder().favoriteCities(favCities).build());
    }
}
