package br.com.lucas.modules.domain;

import com.google.maps.model.GeocodingResult;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Data

public class Address {

    private GeocodingResult[] addressComponents;
    private Double[] coordinates;

    private Address(GeocodingResult[] addressComponents, Double[] coordinates) {
        this.addressComponents = addressComponents;
        this.coordinates = coordinates;
    }

}
