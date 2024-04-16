package br.com.lucas.modules.usecases.impl;

import br.com.lucas.modules.domain.Coordinates;
import br.com.lucas.modules.domain.Location;
import br.com.lucas.modules.infrastructure.gmaps.config.Geocoding;
import br.com.lucas.modules.usecases.CreateLocationUseCase;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class CreateLocationUseCaseImpl implements CreateLocationUseCase {

    private final GeoApiContext context;

    @Autowired
    public CreateLocationUseCaseImpl(GeoApiContext  context) {
        this.context = context;
    }

    public ResponseEntity<Location> createLocation(String postalCode) throws IOException, InterruptedException, ApiException {

        var result = postalCode;
        var loc = new Location();
        var coords = new Coordinates();

        GeocodingResult[] results = GeocodingApi.geocode(context, result).await();

        if(results != null
                && results.length > 0
                && !Objects.isNull(postalCode)){

            Double[] coordsJson = {
                    results[0].geometry.location.lat,
                    results[0].geometry.location.lng
            };
            coords.setType("Point");
            coords.setCoordinates(coordsJson);

            loc.setAddressComponents(results);
            loc.setCoordinates(coords);

            return ResponseEntity.ok(loc);

        }else{

            return ResponseEntity.notFound().build();
        }
    }

}
