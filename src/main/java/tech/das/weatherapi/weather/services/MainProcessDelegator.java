package tech.das.weatherapi.weather.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.das.weatherapi.restClient.ClientConfig;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;
import tech.das.weatherapi.restClient.DTO.TempListFromApi;
import tech.das.weatherapi.weather.datamodels.FavCities;
import tech.das.weatherapi.weather.datamodels.TemperatureList;
import tech.das.weatherapi.weather.datamodels.WeatherReportResponse;
import tech.das.weatherapi.weather.datamodels.WeatherSummaryResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MainProcessDelegator {

    private final ClientConfig apiClient;

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

    public WeatherSummaryResponse getWeatherSummary(List<Integer> locationsToSearch, String unit, BigDecimal temp) {
        List<FavCities> favCities = new ArrayList<>();
        List<ForecastResponse> forecastResponses = new ArrayList<>();
        locationsToSearch.forEach(
                l -> forecastResponses
                        .add(apiClient
                                .getForecastResponseFromApi(String.valueOf(l))));

        forecastResponses
                .stream()
                .forEach(l -> {
                    Optional<TempListFromApi> tempListForTomorrow = l.getListofTemperatures()
                            .stream()
                            .filter(tempListFromApi -> tempListFromApi
                                    .getLocalDate().isEqual(LocalDate.now().plusDays(1)))
                            .filter(p -> p.getTemperatureMasterObject().getCelciusTemp().compareTo(temp) > 1)
                            .findFirst();
                    if (!tempListForTomorrow.isPresent()) {
                        favCities.add(FavCities.builder()
                                .locations(l.getCityDetails().getCityName())
                                .temperature(tempListForTomorrow.get().getTemperatureMasterObject().getCelciusTemp())
                                .build());
                    }
                });
        return (WeatherSummaryResponse.builder().favoriteCities(favCities).build());
    }
}
