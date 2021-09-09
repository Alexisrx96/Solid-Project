package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AircraftDto {

    private int id;
    private String model;
    private int passengersCapacity;
    private float maxFuel;
    private int status;
}
