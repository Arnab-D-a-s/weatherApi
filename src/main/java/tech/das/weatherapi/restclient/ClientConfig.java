package tech.das.weatherapi.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import tech.das.weatherapi.restclient.DTO.ForecastResponse;
import tech.das.weatherapi.restclient.delegators.ExternalResponseDelegator;

@Configuration
@RequiredArgsConstructor
public class ClientConfig {

    private final ExternalResponseDelegator externalResponseDelegator;

    public ForecastResponse getForecastResponseFromApi(String requestLocation){
        return (externalResponseDelegator.getForecastFromApiOrCache(requestLocation));
    }
}
