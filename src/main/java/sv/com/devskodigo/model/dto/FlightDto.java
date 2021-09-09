package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FlightDto {

    public FlightDto(int flightId, String  flightDescriptor, Date flightDateTimeDep, Date flightDateTimeArr, String flightWeatherRpt, int flightCountryIdDep, int flightCityIdDep,  int flightCityIdArr, int flightCountryIdArr, int flightStatus, int aircfratId, int airlineId, int userId){
        this.flightId = flightId;
        this.flightDescriptor = flightDescriptor;
        this.flightDateTimeDep = flightDateTimeDep;
        this.flightDateTimeArr = flightDateTimeArr;
        this.flightCountryIdDep = flightCountryIdDep;
        this.flightCityIdDep = flightCityIdDep;
        this.flightCityIdArr = flightCityIdArr;
        this.flightCountryIdArr = flightCountryIdArr;
        this.flightStatus = flightStatus;
        this.aircfratId = aircfratId;
        this.airlineId = airlineId;
        this.userId = userId;
    }
    public FlightDto(){ }

    private int flightId;
    private String flightDescriptor;
    private Date flightDateTimeDep;
    private Date flightDateTimeArr;
    private String flightWeatherRpt;
    private int flightCountryIdDep;
    private int flightCityIdDep;
    private int flightCityIdArr;
    private int flightCountryIdArr;
    private int flightStatus;
    private int aircfratId;
    private int airlineId;
    private int userId;

    public FlightDto(int anInt, java.sql.Date date, java.sql.Date date1, String string, int anInt1, int anInt2, int anInt3, int anInt4, int anInt5, int anInt6, int anInt7, int anInt8) {
    }
}
