package sv.com.devskodigo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Flight implements DataOperations{
    @Getter
    @Setter
    private int flightId;
    @Getter
    @Setter
    private Date flightDateTimeDep;
    @Getter
    @Setter
    private Date flightDateTimeArr;
    @Getter
    @Setter
    private int flightStatus;
    @Getter
    @Setter
    private char flightWeatherRpt;
    @Getter
    @Setter
    private Aircraft aircraft;
    @Getter
    @Setter
    private City cityDep;
    @Getter
    @Setter
    private City cityArr;
    @Getter
    @Setter
    private User user;

    /*
    @Getter
    @Setter
    private int aircraftId;
    @Getter
    @Setter
    private int airlineId;
    @Getter
    @Setter
    private int flightCountryIdDep;
    @Getter
    @Setter
    private int flightCityIdDep;
    @Getter
    @Setter
    private int flightCountryIdArr;
    @Getter
    @Setter
    private int flightCityIdArr;
    @Getter
    @Setter
    private int userId;
    */

    public Flight(Aircraft aircraft, City cityDep, City cityArr, User user) {
        this.aircraft = aircraft;
        this.cityDep = cityDep;
        this.cityArr = cityArr;
        this.user = user;
    }

    public Flight() {
    }

    @Override
    public void readDataset() {

    }

    @Override
    public void addData() {

    }

    @Override
    public void updateData() {

    }

    @Override
    public void searchData() {

    }

    @Override
    public void deleteData() {

    }

    @Override
    public void updateStatus() {

    }
}
