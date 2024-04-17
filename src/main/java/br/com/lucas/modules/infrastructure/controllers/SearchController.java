package br.com.lucas.modules.infrastructure.controllers;

import br.com.lucas.modules.domain.Address;
import br.com.lucas.modules.domain.Places;
import br.com.lucas.modules.dto.AddressRequest;
import br.com.lucas.modules.usecases.impl.SearchAddressUseCaseImpl;
import br.com.lucas.modules.usecases.impl.SearchUseCaseImpl;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping
@RestController
public class SearchController {

    private final SearchAddressUseCaseImpl createLocation;
    private final SearchUseCaseImpl nearbySearchUseCase;

    @Autowired
    public SearchController(SearchAddressUseCaseImpl createLocation, SearchUseCaseImpl nearbySearchUseCase) {
        this.createLocation = createLocation;
        this.nearbySearchUseCase = nearbySearchUseCase;
    }

    @GetMapping("/{postalCode}/places")
    public ResponseEntity<Address> getAddress(@PathVariable String postalCode) {
        try {
            AddressRequest dto = new AddressRequest(postalCode);
            var loc = createLocation.execute(dto);
            loc.setPlacesList(nearbySearchUseCase.getAllPlaces(loc));
            return ResponseEntity.ok(loc);

        } catch (IOException | InterruptedException | ApiException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{postalCode}")
    public ResponseEntity<List<Places>> getPlace(@PathVariable String postalCode) {
        try {
            AddressRequest dto = new AddressRequest(postalCode);
            var loc = createLocation.execute(dto);
            var places = nearbySearchUseCase.getAllPlaces(loc);
            if (!places.isEmpty()) {
                return ResponseEntity.ok(places);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException | InterruptedException | ApiException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
