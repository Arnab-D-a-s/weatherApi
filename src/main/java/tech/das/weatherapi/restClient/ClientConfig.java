package tech.das.weatherapi.restClient;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;
import tech.das.weatherapi.weather.datamodels.OpenWeatherRequestor;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfig {
    RestTemplate restTemplate = new RestTemplate();

    public ForecastResponse getForecastResponseFromApi(OpenWeatherRequestor requestParms){
        String fooResourceUrl
                //= "http://api.openweathermap.org/data/2.5/weather?q=Copenhagen,dk&units=metric&APPID=74acd621f5eee2fa168e855385f063ee";
        = "https://api.openweathermap.org/data/2.5/forecast?q=Copenhagen,dk&appid=74acd621f5eee2fa168e855385f063ee";
        ForecastResponse response
                = restTemplate.getForObject(fooResourceUrl , ForecastResponse.class);
        return response;
    }
}
