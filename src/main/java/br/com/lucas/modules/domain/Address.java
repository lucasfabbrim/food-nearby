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

    private GeocodingResult[] yourAddress;
    private Double[] coordinates;
    private List<Places> places;

    private Address(GeocodingResult[] addressComponents, Double[] coordinates) {
        this.yourAddress = addressComponents;
        this.coordinates = coordinates;
        this.places = new ArrayList<>();
    }

}
