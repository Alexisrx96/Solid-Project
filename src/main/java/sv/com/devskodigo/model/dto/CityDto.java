package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    private int cityId;
    private String cityName;
    private float cityCoords;
    private int countryId;

    public CityDto() {

    }

    public CityDto(int cityId, String cityName, float cityCoords, int countryId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityCoords = cityCoords;
        this.countryId = countryId;
    }
}
