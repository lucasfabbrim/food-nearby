package br.com.lucas.modules.domain;

import com.google.maps.model.GeocodingResult;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data

public class Address {

    private GeocodingResult[] addressComponents;
    private Double[] coordinates;
    private List<Places> placesList;

    private Address(GeocodingResult[] addressComponents, Double[] coordinates) {
        this.addressComponents = addressComponents;
        this.coordinates = coordinates;
        this.placesList = new ArrayList<>();
    }

}
