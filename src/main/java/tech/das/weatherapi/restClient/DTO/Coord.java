package tech.das.weatherapi.restClient.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coord {
    public BigDecimal longitude;
    public BigDecimal latitude;
}
