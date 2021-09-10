package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightDto {

    private int flightId;
    private Date flightDateTimeDep;
    private Date flightDateTimeArr;
    private String flightWeatherRpt;
    private int flightCityIdDep;
    private int flightCityIdArr;
    private int flightStatus;
    private int aircraftId;
    private int airlineId;
    private int userId;

    public FlightDto(int flightId, Date flightDateTimeDep, Date flightDateTimeArr,
                     String flightWeatherRpt, int flightCityIdDep, int flightCityIdArr,
                     int flightStatus, int aircraftId, int airlineId, int userId) {
        this.flightId = flightId;
        this.flightDateTimeDep = flightDateTimeDep;
        this.flightDateTimeArr = flightDateTimeArr;
        this.flightWeatherRpt = flightWeatherRpt;
        this.flightCityIdDep = flightCityIdDep;
        this.flightCityIdArr = flightCityIdArr;
        this.flightStatus = flightStatus;
        this.aircraftId = aircraftId;
        this.airlineId = airlineId;
        this.userId = userId;
    }

    public FlightDto() {
    }

    @Override
    public String toString() {
        return "Record Information->" +
                "Id=" + flightId +
                ", Datetime Depart=" + flightDateTimeDep + '\'' +
                ", Datetime Arrival=" + flightDateTimeArr + '\'' +
                ", Current Weather=" + flightWeatherRpt +'\''+
                ", Depart=" + flightCityIdDep +'\''+
                ", Arrival="+ flightCityIdArr +'\''+
                ", Status="+ flightStatus +'\''+
                ", Aircraft="+ aircraftId +'\''+
                ", Airline="+ airlineId +'\''+
                ", User=" +userId;
    }
}
