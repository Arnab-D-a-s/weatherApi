package tech.das.weatherapi.restclient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemperatureMasterObject implements Serializable {
    @JsonProperty("temp")
    private BigDecimal temperature;

    public BigDecimal getCelciusTemp() {
        return this.temperature.subtract(BigDecimal.valueOf(273.15)).setScale(2,BigDecimal.ROUND_UP);
    }

    public BigDecimal getFahrenheitTemp() {
        return this.temperature.multiply(BigDecimal.valueOf(1.8)).subtract(BigDecimal.valueOf(459.67));
    }
}
