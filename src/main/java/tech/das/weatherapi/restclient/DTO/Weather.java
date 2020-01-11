package tech.das.weatherapi.restclient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Serializable {
    @JsonProperty("id")
    public Integer someId;
    @JsonProperty("main")
    public String howsTheWeather;
    @JsonProperty("description")
    public String weatherCondition;
    @JsonProperty("icon")
    public String cmsId;
}
