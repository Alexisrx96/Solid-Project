package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightDto {

    private int flightId;
    private String flightDescriptor;
    private Date flightDateTimeDep;
    private Date flightDateTimeArr;
    private int flightStatus;
    private String flightWeatherRpt;
    private int aircfratId;
    private int airlineId;
    private int flightCountryIdDep;
    private int flightCityIdDep;
    private int flightCountryIdArr;
    private int flightCityIdArr;
    private int userId;
}
