package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AircraftDto {


    public AircraftDto(int aircraftId, String aircraftRegistration,String aircraftModel,int aircraftPassengersCapacity, float aircraftMaxFuel, int aircraftStatus){
        this.aircraftId = aircraftId;
        this.aircraftRegistration = aircraftRegistration;
        this.aircraftModel = aircraftModel;
        this.aircraftPassengersCapacity = aircraftPassengersCapacity;
        this.aircraftMaxFuel = aircraftMaxFuel;
        this.aircraftStatus = aircraftStatus;
    }

    public AircraftDto(){}

    private int aircraftId;
    private String aircraftRegistration;
    private String aircraftModel;
    private int aircraftPassengersCapacity;
    private float aircraftMaxFuel;
    private int aircraftStatus;
}
