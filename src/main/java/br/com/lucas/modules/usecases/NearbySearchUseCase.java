package br.com.lucas.modules.usecases;

import br.com.lucas.modules.domain.Location;
import br.com.lucas.modules.domain.Places;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

public interface NearbySearchUseCase {

    public List<Places> getAllPlaces(Location location) throws IOException;
    public String fetch(Location location);
    public HttpRequest receivedHttpRequest(Integer timout, String url);

}
