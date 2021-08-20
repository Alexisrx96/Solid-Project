package sv.com.devskodigo;

import lombok.Getter;
import lombok.Setter;

public class Aircraft{

    @Getter
    @Setter
    private static int x;

    @Getter
    @Setter
    private Airline airline;

    public Aircraft(Airline airline) {
        this.airline = airline;
    }
    public Aircraft() {
    }


}
