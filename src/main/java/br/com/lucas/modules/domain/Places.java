package br.com.lucas.modules.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class Places {

    private String name;
    private String vicinity;
    private Double rating;
    private Double distance;
    private Boolean opened;
    private Double[] coordinates;
    private String[] categories;

    public Places(String name, Boolean opened, Double rating, String vicinity, String[] categories, Double[] coordinates) {
        this.name = name;
        this.opened = opened;
        this.rating = rating;
        this.vicinity = vicinity;
        this.categories = categories;
        this.coordinates = coordinates;
    }

    public void calculateDistance(Double[] coordinateOfLocation){
        double EARTH_RADIUS_KM = 6371.0;
        double lat1 = Math.toRadians(coordinates[0]);
        double lon1 = Math.toRadians(coordinates[1]);
        double lat2 = Math.toRadians(coordinateOfLocation[0]);
        double lon2 = Math.toRadians(coordinateOfLocation[1]);
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * c;
        double roundedDistance = Math.round(distance * 10.0) / 10.0;
        setDistance(roundedDistance);
    }

}
