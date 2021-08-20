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

public class City  {

    @Getter
    @Setter
    private static int x;

    @Getter
    @Setter
    private Country country;

}
