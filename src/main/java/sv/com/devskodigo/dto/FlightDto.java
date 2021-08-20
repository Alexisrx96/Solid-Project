package sv.com.devskodigo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class FlightDto {

    @Getter
    @Setter
    private int flightId;
    @Getter @Setter
    private String flightDescriptor;
    @Getter @Setter
    private Date flightDateTimeDep;
    @Getter @Setter
    private Date flightDateTimeArr;
    @Getter @Setter
    private int flightStatus;
    @Getter @Setter
    private String flightWeatherRpt;
    @Getter @Setter
    private int aircfratId;
    @Getter @Setter
    private int airlineId;
    @Getter @Setter
    private int flightCountryIdDep;
    @Getter @Setter
    private int flightCityIdDep;
    @Getter @Setter
    private int flightCountryIdArr;
    @Getter @Setter
    private int flightCityIdArr;
    @Getter @Setter
    private int userId;
}
