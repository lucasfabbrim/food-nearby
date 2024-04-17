package br.com.lucas.modules.infrastructure.controllers;

import br.com.lucas.modules.LocationDTO;
import br.com.lucas.modules.domain.Location;
import br.com.lucas.modules.domain.Places;
import br.com.lucas.modules.usecases.impl.CreateLocationUseCaseImpl;
import br.com.lucas.modules.usecases.impl.NearbySearchUseCaseImpl;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RequestMapping
@RestController
public class LocationController {

    private final CreateLocationUseCaseImpl createLocation;
    private final NearbySearchUseCaseImpl nearbySearchUseCase;

    @Autowired
    public LocationController(CreateLocationUseCaseImpl createLocation, NearbySearchUseCaseImpl nearbySearchUseCase) {
        this.createLocation = createLocation;
        this.nearbySearchUseCase = nearbySearchUseCase;
    }

    @PostMapping("/search")
    public ResponseEntity<Location> createLocation(@RequestBody LocationDTO location) {
        try {
            var loc = createLocation.execute(location);
            return ResponseEntity.ok(loc);
        } catch (IOException | InterruptedException | ApiException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{postalCode}")
    public ResponseEntity<List<Places>> getLocation(@PathVariable String postalCode) {
        try {
            LocationDTO dto = new LocationDTO(postalCode);
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
