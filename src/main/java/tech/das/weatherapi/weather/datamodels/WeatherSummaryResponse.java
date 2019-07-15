package tech.das.weatherapi.weather.datamodels;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class WeatherSummaryResponse implements Serializable {

    private List<FavCities> favoriteCities;

}
