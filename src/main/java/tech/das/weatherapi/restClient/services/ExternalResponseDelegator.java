package tech.das.weatherapi.restClient.services;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ExternalResponseDelegator {
    RestTemplate restTemplate = new RestTemplate();

    @Value(("${swagger.application.apikey}"))
    private String apiKey;

    //SomeCache
    public ForecastResponse getForecastFromApiOrCache(String locationCode) {
        log.info("Calling the Third Party API for LocationCode" + locationCode);
        String apiUrl
                = "https://api.openweathermap.org/data/2.5/forecast?id=" + locationCode + "&appid=" + apiKey;
        return restTemplate.getForObject(apiUrl, ForecastResponse.class);
    }
}
