package tech.das.weatherapi.restClient;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;
import tech.das.weatherapi.restClient.delegators.ExternalResponseDelegator;
import tech.das.weatherapi.weather.datamodels.OpenWeatherRequestor;

@Configuration
@RequiredArgsConstructor
public class ClientConfig {

    private final ExternalResponseDelegator externalResponseDelegator;

    public ForecastResponse getForecastResponseFromApi(OpenWeatherRequestor requestParms){
        ForecastResponse response= (externalResponseDelegator.getForecastFromApiOrCache(requestParms.getLocation()));
        return response;
    }
}
