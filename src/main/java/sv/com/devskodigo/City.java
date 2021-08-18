package sv.com.devskodigo;

/*
name: Country.java
purpose: manage city catalog
author: hftamayo
comments:
1. code the class

 */

import lombok.Getter;
import lombok.Setter;

public class City implements DataOperations {

    @Getter
    @Setter
    private static int x;

    @Getter
    @Setter
    private Country country;

    public City(Country country) {
        this.country = country;
    }

    public City() {
    }

    @Override
    public void readDataset(){

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
