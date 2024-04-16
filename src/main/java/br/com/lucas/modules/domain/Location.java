package br.com.lucas.modules.domain;

import com.google.maps.model.GeocodingResult;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Data

public class Location {

    private GeocodingResult[] addressComponents;
    private Coordinates coordinates;

    private Location(GeocodingResult[] addressComponents, Coordinates coordinates) {
        this.addressComponents = addressComponents;
        this.coordinates = coordinates;
    }

}
