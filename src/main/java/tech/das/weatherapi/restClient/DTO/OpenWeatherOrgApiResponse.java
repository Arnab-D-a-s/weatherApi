package tech.das.weatherapi.restClient.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class OpenWeatherOrgApiResponse implements Serializable {


    public Coord coord;
    @JsonProperty("weather")
    public List<Weather> weatherDetails;
    @JsonProperty("base")
    public String base;
/*    public MainDetails mainDetails;
    public Integer visibility;
    public WindDetails windDetails;
    public CloudDetails cloudDetails;
    public InternalSystemDetails internalSystemDetails;*/

}
