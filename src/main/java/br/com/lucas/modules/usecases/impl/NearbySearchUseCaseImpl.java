package br.com.lucas.modules.usecases.impl;

import br.com.lucas.modules.domain.*;
import br.com.lucas.modules.usecases.NearbySearchUseCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NearbySearchUseCaseImpl implements NearbySearchUseCase {

    @Override
    public List<Places> getAllPlaces(Location location) throws IOException {

        List<Places> places = new ArrayList<>();
        var url = fetch(location);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = receivedHttpRequest(10, url);

        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        future.thenApply(HttpResponse::body).thenAccept(result -> {
            JSONObject obj = new JSONObject(result);
            JSONArray arrays = obj.getJSONArray("results");
            for (int i = 0; i < arrays.length(); i++) {

                JSONObject placeObj = arrays.getJSONObject(i);

                String name = placeObj.getString("name");
                String vicinity = placeObj.getString("vicinity");
                Double ratings = placeObj.getDouble("rating");

                JSONArray typesArray = placeObj.getJSONArray("types");
                String[] categories = new String[typesArray.length()];

                for (int j = 0; j < typesArray.length(); j++) {
                    categories[j] = typesArray.getString(j);
                }

                var cate = new Category(categories);

                Boolean opened = null;
                if (placeObj.has("opening_hours")) {
                    JSONObject openingHours = placeObj.getJSONObject("opening_hours");
                    if (openingHours.has("open_now")) {
                        opened = openingHours.getBoolean("open_now");
                    }
                }


                JSONObject geometry = placeObj.getJSONObject("geometry");
                JSONObject locationObj = geometry.getJSONObject("location");

                Double[] coordsJson = {
                        locationObj.getDouble("lat"),
                        locationObj.getDouble("lng"),
                };
                var coords = new Coordinates("Point", coordsJson);
                var distance = new Distance(coords, location.getCoordinates());
                var distanceMessage = distance.formatDistance();
                var place = new Places(name, opened, ratings, vicinity, distanceMessage, cate, coords);
                places.add(place);
            }
        }).join();

        return places;
    }

    @Override
    public String fetch(Location location) {
        String infos =
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + location.getCoordinates().getCoordinates()[0] + "," + location.getCoordinates().getCoordinates()[1] +
                "&radius=" + 5000 +
                "&type=restaurant" +
                "&key=AIzaSyDbNuKhoNDz5-URRQdks6LI0BxnqucK8cs";

        return infos;
    }

    @Override
    public HttpRequest receivedHttpRequest(Integer timout, String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofSeconds(timout)).build();
    }
}
