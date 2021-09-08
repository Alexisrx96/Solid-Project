package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

public class AircraftDto {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private String model;
    @Getter @Setter
    private int passengersCapacity;
    @Getter @Setter
    private float maxFuel;
    @Getter @Setter
    private int status;
}
