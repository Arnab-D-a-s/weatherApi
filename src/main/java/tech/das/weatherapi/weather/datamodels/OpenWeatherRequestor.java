package tech.das.weatherapi.weather.datamodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenWeatherRequestor {

    private String location;
    private String countryCode;

}
