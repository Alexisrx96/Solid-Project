package sv.com.devskodigo;

/*
name: Country.java
purpose: manage country catalog
author: hftamayo
comments:
1. code the class

 */

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Country implements DataOperations {

    @Getter
    @Setter
    private int countryId;
    private String countryName;
    private float countryCoords;

    public Country(){
        readDataset();
    }

    @Override
    public void readDataset(){
        System.out.println("Hey buddy!");

    }

    @Override
    public void addData(){

    }

    @Override
    public void updateData(){

    }

    @Override
    public void searchData(){

    }

    @Override
    public void deleteData(){

    }

    @Override
    public void updateStatus(){

    }
}
