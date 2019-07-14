package tech.das.weatherapi.weather.datamodels;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherReportResponse implements Serializable {

    private List<TemperatureList> temperatureLists;

}
