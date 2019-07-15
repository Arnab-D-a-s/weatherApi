package tech.das.weatherapi.weather.datamodels;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class FavCities implements Serializable{
    private String locations;
    private BigDecimal temperature;
}
