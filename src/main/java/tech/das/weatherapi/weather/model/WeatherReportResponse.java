package tech.das.weatherapi.weather.model;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherReportResponse implements Serializable {

    private String dummy;
}
