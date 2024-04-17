package br.com.lucas.modules.usecases.impl;

import br.com.lucas.modules.domain.Address;
import br.com.lucas.modules.dto.AddressRequest;
import br.com.lucas.modules.usecases.SearchAddressUseCase;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchAddressUseCaseImpl implements SearchAddressUseCase {

    private final GeoApiContext context;

    @Autowired
    public SearchAddressUseCaseImpl(GeoApiContext  context) {
        this.context = context;
    }

    public Address execute(AddressRequest postalCode) throws IOException, InterruptedException, ApiException {

        var result = postalCode.postalCode();
        var loc = new Address();

        GeocodingResult[] results = GeocodingApi.geocode(context, result).await();

        if(results != null && results.length > 0){

            Double[] coordsJson = {
                    results[0].geometry.location.lat,
                    results[0].geometry.location.lng
            };

            loc.setAddressComponents(results);
            loc.setCoordinates(coordsJson);

            return loc;
        }else{
            throw new NotFoundException("no location found");
        }
    }

}
