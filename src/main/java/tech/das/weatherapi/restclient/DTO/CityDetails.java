package tech.das.weatherapi.restclient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDetails implements Serializable {

    @JsonProperty("name")
    private String cityName;
    @JsonProperty("id")
    private Integer cityCode;

}
