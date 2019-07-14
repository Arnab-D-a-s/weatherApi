package tech.das.weatherapi.restClient.delegators;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.das.weatherapi.restClient.DTO.ForecastResponse;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExternalResponseDelegator {

    @Value("${swagger.application.apikey}")
    private String apiKey;

    public ForecastResponse getForecastFromApiOrCache(String locationCode) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Calling the Third Party API for LocationCode" + locationCode);
        String apiUrl
                = "https://api.openweathermap.org/data/2.5/forecast?id=" + locationCode + "&appid=" + apiKey;
        return restTemplate.getForObject(apiUrl, ForecastResponse.class);
    }
}
