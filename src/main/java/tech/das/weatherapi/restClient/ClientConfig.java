package tech.das.weatherapi.restClient;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;
import tech.das.weatherapi.restClient.services.ExternalResponseDelegator;
import tech.das.weatherapi.weather.model.OpenWeatherRequestor;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfig {

    private ExternalResponseDelegator externalResponseDelegator;

    public ForecastResponse getForecastResponseFromApi(OpenWeatherRequestor requestParms){
        ForecastResponse response= (externalResponseDelegator.getForecastFromApiOrCache(requestParms.getLocation()));
        return response;
    }
}
