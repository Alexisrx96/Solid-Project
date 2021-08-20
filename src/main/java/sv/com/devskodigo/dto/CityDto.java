package sv.com.devskodigo;

/*
name: City.java
purpose: manage city catalog
author: hftamayo
comments:
1. send a confirmation if dataSet was successfull

 */

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class CityDto{
    @Getter @Setter
    private int cityId;
    @Getter @Setter
    private String cityName;
    @Getter @Setter
    private float cityCoords;
    @Getter @Setter
    private int countryId;

}
