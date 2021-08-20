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

public class Country {

    @Getter
    @Setter
    private int countryId;
    private String countryName;
    private float countryCoords;

}
