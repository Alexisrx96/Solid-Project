package sv.com.devskodigo.controller;

import sv.com.devskodigo.model.dao.FlightDao;
import sv.com.devskodigo.model.dto.FlightDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class FlightController implements ICrudOperations {
    private Date flightDateTimeDepart;
    private Date flightDateTimeArrival;
    private String flightWeatherReport = "";
    private int flightCityDepartId = 0;
    private int flightCityArrivalId = 0;
    private int statusId = 0;
    private int aircraftId = 0;
    private int airlineId = 0;
    private int userId = 0;

    private Scanner rawData;
    private int requestedAction;
    private int targetId = 0;
    private FlightDto flight;

    public FlightController(int ra){
        this.requestedAction = ra;
        this.crudPipeline();

    }

    @Override
    public void crudPipeline(){
        switch(requestedAction){
            case 1: //insert
                this.getData();
                this.saveData();
                break;

            case 2://display
                this.viewData();
                break;

            case 3: //delete
                targetId = this.searchData();
                if(targetId > 0){
                    this.deleteData(targetId);
                }
                break;

            case 4: //update
                targetId = this.searchData();
                if(targetId > 0){
                    this.getData();
                    this.updateData(targetId);
                }
                break;
        }
    }

    @Override
    public void getData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rawData = new Scanner(System.in);
        System.out.println("Please type the below requested about flight information: ");
        System.out.println("Date Time of Departure:");
        try{
            flightDateTimeDepart = dateFormat.parse(rawData.nextLine());
            System.out.println("Date Time of Arrival: (yyyy-MM-dd HH:mm:ss)");
            flightDateTimeArrival = dateFormat.parse(rawData.nextLine());
        }catch(ParseException pe){
            System.out.println("Error parsing date-time information");
        }

        System.out.println("Weather Report:");
        flightWeatherReport = rawData.nextLine();
        System.out.println("City of Departure:");
        flightCityDepartId = rawData.nextInt();
        rawData.nextLine();
        System.out.println("City of Arrival:");
        flightCityArrivalId = rawData.nextInt();
        rawData.nextLine();
        System.out.println("Flight Status:");
        statusId = rawData.nextInt();
        rawData.nextLine();
        System.out.println("Aircraft Id:");
        aircraftId = rawData.nextInt();
        rawData.nextLine();
        System.out.println("Airline Id:");
        airlineId = rawData.nextInt();
        rawData.nextLine();
        System.out.println("User Id:");
        userId = rawData.nextInt();
        rawData.nextLine();
    }


    @Override
    public void saveData(){
        FlightDao flightDao =  new FlightDao();
        flightDao.insert(new FlightDto(0, this.flightDateTimeDepart, this.flightDateTimeArrival,
                this.flightWeatherReport, this.flightCityDepartId, this.flightCityArrivalId,
                this.statusId, this.aircraftId, this.airlineId, this.userId));
    }


    @Override
    public void viewData(){
        FlightDao flightDao = new FlightDao();
        for(var c: flightDao.getList()){
            System.out.println(c);
        }
    }

    @Override
    public int searchData(){
        rawData = new Scanner(System.in);
        int idFound = 0;
        System.out.println("Please type a valid id:");
        int idSearch = rawData.nextInt();
        FlightDao flightDao = new FlightDao();
        flight = flightDao.read(idSearch);
        if(flight == null){
            System.out.println("No records found");
        }
        else{
            System.out.println("====================");
            System.out.println("Displaying flight information found:");
            System.out.println("Date/Time Dep: "+"\t"+flight.getFlightDateTimeDep());
            System.out.println("Date/Time Arr: "+"\t"+flight.getFlightDateTimeArr());
            System.out.println("Weather Report: "+"\t"+flight.getFlightWeatherRpt());
            System.out.println("City of Departure: "+"\t"+flight.getFlightCityIdDep());
            System.out.println("City of Arrival: "+"\t"+flight.getFlightCityIdArr());
            System.out.println("Flight Status: "+"\t"+flight.getFlightStatus());
            System.out.println("Aircraft Id: "+"\t"+flight.getAircraftId());
            System.out.println("Airline Id: "+"\t"+flight.getAirlineId());
            System.out.println("User Id: "+"\t"+flight.getUserId());

            System.out.println("====================");
            idFound = flight.getFlightId();
        }
        return idFound;
    }

    @Override
    public void deleteData(int id){
        FlightDao flightDao = new FlightDao();
        flightDao.delete(id);
        System.out.println("Record deleted");

    }

    @Override
    public void updateData(int id){
        FlightDao flightDao = new FlightDao();
        flightDao.update(new FlightDto(id, this.flightDateTimeDepart, this.flightDateTimeArrival,
                this.flightWeatherReport, this.flightCityDepartId, this.flightCityArrivalId,
                this.statusId, this.aircraftId, this.airlineId, this.userId));
        System.out.println("Record updated");
    }

}
