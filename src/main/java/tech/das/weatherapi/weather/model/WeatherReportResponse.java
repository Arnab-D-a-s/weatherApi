package tech.das.weatherapi.weather.model;

import lombok.*;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherReportResponse implements Serializable {

    private List<TemperatureList> temperatureLists;

}
