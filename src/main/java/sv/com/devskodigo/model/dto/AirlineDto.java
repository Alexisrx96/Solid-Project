package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineDto {
    private int airlineId;
    private String airlineName;

    public AirlineDto(int airlineId, String airlineName) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
    }

    public AirlineDto() {
    }

    @Override
    public String toString() {
        return "Record Information->" +
                "Id=" + airlineId +
                ", Name=" + airlineName;
    }
}
