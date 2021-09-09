package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AirlineDto {
    public AirlineDto(int airlineId, String airlineName) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
    }

    public AirlineDto(int anInt, String string, String rsString, int rsInt, float aDouble, int i){}

    private int airlineId;
    private String airlineName;
}
