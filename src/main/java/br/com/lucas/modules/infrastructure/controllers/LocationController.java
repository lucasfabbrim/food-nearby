package br.com.lucas.modules.infrastructure.controllers;

import br.com.lucas.modules.LocationDTO;
import br.com.lucas.modules.domain.Location;
import br.com.lucas.modules.usecases.impl.CreateLocationUseCaseImpl;
import com.google.maps.errors.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping
@RestController
public class LocationController {

    private final CreateLocationUseCaseImpl createLocation;

    @Autowired
    public LocationController(CreateLocationUseCaseImpl createLocation) {
        this.createLocation = createLocation;
    }

    @PostMapping("/search")
    public ResponseEntity<Location> createLocation(@RequestBody LocationDTO location) {
        System.out.println(location.postalCode());
        try {
            var loc = createLocation.execute(location);
            return ResponseEntity.ok(loc);
        } catch (IOException | InterruptedException | ApiException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
