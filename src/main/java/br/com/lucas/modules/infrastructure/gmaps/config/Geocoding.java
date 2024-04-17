package br.com.lucas.modules.infrastructure.gmaps.config;

import com.google.maps.GeoApiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class Geocoding {

    @Bean
    public GeoApiContext execute(){

        Logger.getLogger(getClass().getPackageName()).log(Level.WARNING, "Initialized.");

        return new GeoApiContext
                        .Builder()
                        .apiKey("AIzaSyDbNuKhoNDz5-URRQdks6LI0BxnqucK8cs")
                        .build();
    }
}
