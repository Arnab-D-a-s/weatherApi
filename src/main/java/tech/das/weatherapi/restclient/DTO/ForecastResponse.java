package tech.das.weatherapi.restclient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponse implements Serializable {

    @JsonProperty("cod")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("city")
    private CityDetails cityDetails;

    @JsonProperty("list")
    private List<TempListFromApi> listofTemperatures;


}
