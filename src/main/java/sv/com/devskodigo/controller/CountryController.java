package sv.com.devskodigo.controller;

import sv.com.devskodigo.model.dao.CountryDao;
import sv.com.devskodigo.model.dto.CountryDto;

import java.util.Scanner;

public class CountryController implements ICrudOperations {
    private String countryName = "";
    private float countryCoords = 0;
    private Scanner rawData;
    private int requestedAction;
    private int targetId = 0;
    private CountryDto country;

    public CountryController(int ra){
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
        countryName = rawData.nextLine();
        System.out.println("Country's GPS Coords");
        countryCoords = rawData.nextFloat();

    }

    @Override
    public void saveData(){
        CountryDao countryDao =  new CountryDao();
        countryDao.insert(new CountryDto(0, this.countryName, this.countryCoords));
    }

    @Override
    public void viewData(){
        CountryDao countryDao = new CountryDao();
        for(var c: countryDao.getList()){
            System.out.println(c);
        }
    }

    @Override
    public int searchData(){
        rawData = new Scanner(System.in);
        int idFound = 0;
        System.out.println("Please type a valid id:");
        int idSearch = rawData.nextInt();
        CountryDao countryDao = new CountryDao();
        country = countryDao.read(idSearch);
        if(country == null){
            System.out.println("No records found");
        }
        else{
            System.out.println("====================");
            System.out.println("Displaying information found:");
            System.out.println("Country's name: "+"\t"+country.getCountryName());
            System.out.println("Country's coords:"+"\t"+country.getCountryCoords());
            System.out.println("====================");
            idFound = country.getCountryId();
        }
        return idFound;
    }

    @Override
    public void deleteData(int id){
        CountryDao countryDao = new CountryDao();
        countryDao.delete(id);
        System.out.println("Record deleted");

    }

    @Override
    public void updateData(int id){
        CountryDao countryDao = new CountryDao();
        countryDao.update(new CountryDto(id, this.countryName, this.countryCoords));
        System.out.println("Record updated");
    }

}