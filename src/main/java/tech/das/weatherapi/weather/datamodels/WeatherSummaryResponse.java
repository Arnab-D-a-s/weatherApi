package tech.das.weatherapi.weather.datamodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class WeatherSummaryResponse implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private List<FavCities> favoriteCities;

}
