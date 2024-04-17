package br.com.lucas.modules.usecases;

import br.com.lucas.modules.LocationDTO;
import br.com.lucas.modules.domain.Location;
import com.google.maps.errors.ApiException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface CreateLocationUseCase {

    public Location execute(LocationDTO postalCode) throws IOException, InterruptedException, ApiException;

}
