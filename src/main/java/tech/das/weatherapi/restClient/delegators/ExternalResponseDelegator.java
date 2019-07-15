package tech.das.weatherapi.restClient.delegators;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;
import tech.das.weatherapi.weather.datamodels.TemperatureList;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExternalResponseDelegator {

    @Value("${swagger.application.apikey}")
    private String apiKey;

    @Cacheable(value="forecastCache")
    public ForecastResponse getForecastFromApiOrCache(String locationCode) {
        RestTemplate restTemplate = new RestTemplate();
        ForecastResponse forecastResponse = new ForecastResponse();
        log.info("Calling the Third Party API for LocationCode {}" , locationCode);
        String apiUrl
                = "https://api.openweathermap.org/data/2.5/forecast?id=" + locationCode + "&appid=" + apiKey;
        try {
            forecastResponse = restTemplate.getForObject(apiUrl, ForecastResponse.class);
            return forecastResponse;
        } catch (RestClientResponseException e) {
            return (ForecastResponse.builder().code(400).message("Error From Server, PSSST!!!! Maybe check the location codes ;) :" + e.getResponseBodyAsString()).build());
        }
    }

}
