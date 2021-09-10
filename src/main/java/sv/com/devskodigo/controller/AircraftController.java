package sv.com.devskodigo.controller;

import sv.com.devskodigo.model.dao.AircraftDao;
import sv.com.devskodigo.model.dto.AircraftDto;

import java.util.Scanner;

public class AircraftController implements ICrudOperations {
    private String aircraftRegistration = "";
    private String aircraftModel = "";
    private int aircraftPassenger = 0;
    private double aircraftFuelRange = 0;
    private int statusId = 0;

    private Scanner rawData;
    private int requestedAction;
    private int targetId = 0;
    private AircraftDto aircraft;

    public AircraftController(int ra){
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
        rawData = new Scanner(System.in);
        System.out.println("Please type the below requested about aircraft information: ");
        System.out.println("Registration:");
        aircraftRegistration = rawData.nextLine();
        System.out.println("Model:");
        aircraftModel = rawData.nextLine();
        System.out.println("Max Number of Passengers:");
        aircraftPassenger = rawData.nextInt();
        rawData.nextLine();
        System.out.println("Max Range per full gas tank:");
        aircraftFuelRange = rawData.nextDouble();
        rawData.nextLine();
        System.out.println("Current Aircraft's Status:");
        statusId = rawData.nextInt();
        rawData.nextLine();
    }

    @Override
    public void saveData(){
        AircraftDao aircraftDao =  new AircraftDao();
        aircraftDao.insert(new AircraftDto(0, this.aircraftRegistration, this.aircraftModel,
                this.aircraftPassenger, (float) this.aircraftFuelRange, this.statusId));
    }

    @Override
    public void viewData(){
        AircraftDao aircraftDao = new AircraftDao();
        for(var c: aircraftDao.getList()){
            System.out.println(c);
        }
    }

    @Override
    public int searchData(){
        rawData = new Scanner(System.in);
        int idFound = 0;
        System.out.println("Please type a valid id:");
        int idSearch = rawData.nextInt();
        AircraftDao aircraftDao = new AircraftDao();
        aircraft = aircraftDao.read(idSearch);
        if(aircraft == null){
            System.out.println("No records found");
        }
        else{
            System.out.println("====================");
            System.out.println("Displaying aircraft information found:");
            System.out.println("Registration: "+"\t"+aircraft.getAircraftRegistration());
            System.out.println("Model: "+"\t"+aircraft.getAircraftModel());
            System.out.println("Passenger's Max: "+"\t"+aircraft.getAircraftPassengersCapacity());
            System.out.println("Max Range per tank: "+"\t"+aircraft.getAircraftMaxFuel());
            System.out.println("Current Aircraft Status: "+"\t"+aircraft.getAircraftStatus());
            System.out.println("====================");
            idFound = aircraft.getAircraftId();
        }
        return idFound;
    }

    @Override
    public void deleteData(int id){
        AircraftDao aircraftDao = new AircraftDao();
        aircraftDao.delete(id);
        System.out.println("Record deleted");

    }

    @Override
    public void updateData(int id){
        AircraftDao aircraftDao = new AircraftDao();
        aircraftDao.update(new AircraftDto(id, this.aircraftRegistration, this.aircraftModel,
                this.aircraftPassenger, (float) this.aircraftFuelRange, this.statusId));
        System.out.println("Record updated");
    }

}
