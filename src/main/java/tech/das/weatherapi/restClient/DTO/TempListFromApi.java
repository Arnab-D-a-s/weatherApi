package tech.das.weatherapi.restClient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempListFromApi implements Serializable {
    @JsonProperty("dt")
    private Long date;
    @JsonProperty("main")
    private TemperatureMasterObject temperatureMasterObject;

    public Date getUsableDate () {

        Long date = this.date;
        Date response = new Date();
        response.setTime((long)date*1000);
        return (response);

    }

    public LocalDate getLocalDate () {
        Date date1 = this.getUsableDate();
        return date1.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
