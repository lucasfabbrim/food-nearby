package br.com.lucas;

import br.com.lucas.modules.infrastructure.gmaps.config.Geocoding;
import br.com.lucas.modules.usecases.impl.CreateLocationUseCaseImpl;
import com.google.maps.errors.ApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FoodNearbyApplication {


	public static void main(String[] args) throws IOException, InterruptedException, ApiException {
		SpringApplication.run(FoodNearbyApplication.class, args);
	}

}
