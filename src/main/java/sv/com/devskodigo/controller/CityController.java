package sv.com.devskodigo.controller;

import sv.com.devskodigo.model.dao.CityDao;
import sv.com.devskodigo.model.dto.CityDto;

import java.util.Scanner;

public class CityController implements ICrudOperations {
    private String cityName = "";
    private float cityCoords = 0;
    private int countryId = 0;

    private Scanner rawData;
    private int requestedAction;
    private int targetId = 0;
    private CityDto city;

    public CityController(int ra){
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
        System.out.println("City's name");
        cityName = rawData.nextLine();
        System.out.println("GPS coords");
        cityCoords = rawData.nextInt();
        System.out.println("Country ID");
        countryId = rawData.nextInt();

    }

    @Override
    public void saveData(){
        CityDao cityDao =  new CityDao();
        cityDao.insert(new CityDto(0, this.cityName, this.cityCoords, this.countryId));
    }

    @Override
    public void viewData(){
        CityDao cityDao = new CityDao();
        for(var c: cityDao.getList()){
            System.out.println(c);
        }
    }

    @Override
    public int searchData(){
        rawData = new Scanner(System.in);
        int idFound = 0;
        System.out.println("Please type a valid id:");
        int idSearch = rawData.nextInt();
        CityDao cityDao = new CityDao();
        city = cityDao.read(idSearch);
        if(city == null){
            System.out.println("No records found");
        }
        else{
            System.out.println("====================");
            System.out.println("Displaying information found:");
            System.out.println("City's name: "+"\t"+city.getCityName());
            System.out.println("GPS coords: "+"\t"+city.getCityCoords());
            System.out.println("Country Id: "+"\t"+city.getCountryId());
            System.out.println("====================");
            idFound = city.getCityId();
        }
        return idFound;
    }

    @Override
    public void deleteData(int id){
        CityDao cityDao = new CityDao();
        cityDao.delete(id);
        System.out.println("Record deleted");

    }

    @Override
    public void updateData(int id){
        CityDao cityDao = new CityDao();
        cityDao.update(new CityDto(id, this.cityName, this.cityCoords, this.countryId));
        System.out.println("Record updated");
    }

}
