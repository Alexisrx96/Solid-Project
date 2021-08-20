package sv.com.devskodigo.dto;

import lombok.Getter;
import lombok.Setter;

public class CountryDto {
    @Getter
    @Setter
    private int countryId;
    @Getter
    @Setter
    private String countryName;
    @Getter
    @Setter
    private float countryCoords;
}
