package br.com.lucas.modules.usecases.impl;

import br.com.lucas.modules.domain.*;
import br.com.lucas.modules.usecases.SearchUseCase;
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
public class SearchUseCaseImpl implements SearchUseCase {

    @Override
    public List<Places> getAllPlaces(Address location) throws IOException {

        List<Places> places = new ArrayList<>();

        var url = fetch(location);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = receivedHttpRequest(10, url);

        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        future.thenApply(HttpResponse::body).thenAccept(result -> {
            JSONObject obj = new JSONObject(result);
            JSONArray arrays = obj.getJSONArray("results");

            arrays.forEach(placeObj -> {
                JSONObject jsonObject = (JSONObject) placeObj;

                var place = new Places();
                place.setName(jsonObject.getString("name"));
                place.setVicinity(jsonObject.getString("vicinity"));
                //place.setRating(jsonObject.getDouble("rating"));

                JSONArray typesArray = jsonObject.getJSONArray("types");
                String[] categories = new String[typesArray.length()];
                for (int j = 0; j < typesArray.length(); j++) {
                    categories[j] = typesArray.getString(j);
                }

                if (jsonObject.has("opening_hours")) {
                    JSONObject openingHours = jsonObject.getJSONObject("opening_hours");
                    if (openingHours.has("open_now")) {
                        place.setOpened(openingHours.getBoolean("open_now"));
                    }
                }

                JSONObject geometry = jsonObject.getJSONObject("geometry");
                JSONObject locationObj = geometry.getJSONObject("location");

                Double[] coordsJson = {
                        locationObj.getDouble("lat"),
                        locationObj.getDouble("lng"),
                };

                Double[] coordsDistance = {
                        location.getCoordinates()[0],
                        location.getCoordinates()[1],
                };

                place.setCategories(categories);
                place.setCoordinates(coordsJson);
                place.calculateDistance(coordsDistance);
                places.add(place);
            });
        }).join();

        return places;
    }

    @Override
    public String fetch(Address location) {

        var lat = location.getCoordinates()[0];
        var lng = location.getCoordinates()[1];

        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + lat + "," + lng +
                "&radius=" + 2000 +
                "&types=restaurant" +
                "&key=AIzaSyDbNuKhoNDz5-URRQdks6LI0BxnqucK8cs";
    }

    @Override
    public HttpRequest receivedHttpRequest(Integer timout, String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofSeconds(timout)).build();
    }
}
