package br.com.lucas.modules.usecases;

import br.com.lucas.modules.domain.Location;
import com.google.maps.errors.ApiException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface CreateLocationUseCase {

    public ResponseEntity<Location> createLocation(String postalCode) throws IOException, InterruptedException, ApiException;

}
