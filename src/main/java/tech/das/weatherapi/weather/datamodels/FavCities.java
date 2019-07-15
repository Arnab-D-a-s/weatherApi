package tech.das.weatherapi.weather.datamodels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class FavCities implements Serializable{
    private String city;
    private Integer locationCode;
    @JsonIgnore
    private BigDecimal temperature;
}
