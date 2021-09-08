package sv.com.devskodigo.model.dto;

/*
name: City.java
purpose: manage city catalog
author: hftamayo
comments:
1. send a confirmation if dataSet was successfull

 */

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CityDto{
    public CityDto(){

    }

    public CityDto(int cityId, String cityName, float cityCoords, int countryId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityCoords = cityCoords;
        this.countryId = countryId;
    }

    private int cityId;
    private String cityName;
    private float cityCoords;
    private int countryId;
}
