package br.com.lucas.modules.infrastructure.gmaps.config;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Geocoding {

    @Bean
    public GeoApiContext execute(){
       return new GeoApiContext.Builder().apiKey("AIzaSyDbNuKhoNDz5-URRQdks6LI0BxnqucK8cs").build();
    }
}
