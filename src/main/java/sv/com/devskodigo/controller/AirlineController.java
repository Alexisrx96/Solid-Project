package sv.com.devskodigo.controller;

import sv.com.devskodigo.model.dao.AirlineDao;
import sv.com.devskodigo.model.dto.AirlineDto;

import java.util.Scanner;

public class AirlineController implements ICrudOperations {
    private String airlineName = "";
    private Scanner rawData;
    private int requestedAction;
    private int targetId = 0;
    private AirlineDto airline;

    public AirlineController(int ra){
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
        System.out.println("Please type the below requested information: ");
        System.out.println("Country's name");
        airlineName = rawData.nextLine();

    }

    @Override
    public void saveData(){
        AirlineDao airlineDao =  new AirlineDao();
        airlineDao.insert(new AirlineDto(0, this.airlineName));
    }

    @Override
    public void viewData(){
        AirlineDao airlineDao = new AirlineDao();
        for(var c: airlineDao.getList()){
            System.out.println(c);
        }
    }

    @Override
    public int searchData(){
        rawData = new Scanner(System.in);
        int idFound = 0;
        System.out.println("Please type a valid id:");
        int idSearch = rawData.nextInt();
        AirlineDao airlineDao = new AirlineDao();
        airline = airlineDao.read(idSearch);
        if(airline == null){
            System.out.println("No records found");
        }
        else{
            System.out.println("====================");
            System.out.println("Displaying information found:");
            System.out.println("Airline's name: "+"\t"+airline.getAirlineName());
            System.out.println("====================");
            idFound = airline.getAirlineId();
        }
        return idFound;
    }

    @Override
    public void deleteData(int id){
        AirlineDao airlineDao = new AirlineDao();
        airlineDao.delete(id);
        System.out.println("Record deleted");

    }

    @Override
    public void updateData(int id){
        AirlineDao airlineDao = new AirlineDao();
        airlineDao.update(new AirlineDto(id, this.airlineName));
        System.out.println("Record updated");
    }

}
