package br.com.lucas;

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
