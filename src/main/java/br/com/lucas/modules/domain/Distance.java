package br.com.lucas.modules.domain;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Distance {

    private Coordinates coordsPlace;
    private Coordinates coordsLocation;
    private Double EARTH_RADIUS_KM;

    public Distance(Coordinates coordsPlace, Coordinates coordsLocation) {
        this.coordsPlace = coordsPlace;
        this.coordsLocation = coordsLocation;
        this.EARTH_RADIUS_KM  = 6371.0;
    }

    public double calculateDistance() {
        double lat1 = Math.toRadians(coordsPlace.getCoordinates()[0]);
        double lon1 = Math.toRadians(coordsPlace.getCoordinates()[1]);
        double lat2 = Math.toRadians(coordsLocation.getCoordinates()[0]);
        double lon2 = Math.toRadians(coordsLocation.getCoordinates()[1]);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * c;

        return distance;
    }
    public String formatDistance() {
        var distance = calculateDistance();
        if (distance >= 1000) {
            double distanceKm = distance / 1000.0;
            return String.format("%.2f km", distanceKm);
        } else {
            String distanceStr = String.format("%.2f", distance);
            distanceStr = distanceStr.replaceAll("^0+,", "");
            if (distanceStr.startsWith(".")) {
                distanceStr = "0" + distanceStr;
            }
            return distanceStr.replaceAll(",", "") + "m";
        }
    }

}
