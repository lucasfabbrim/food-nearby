package br.com.lucas.modules.usecases;

import br.com.lucas.modules.domain.Address;
import br.com.lucas.modules.domain.Places;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

public interface SearchUseCase {

    public List<Places> getAllPlaces(Address location) throws IOException;
    public String fetch(Address location);
    public HttpRequest receivedHttpRequest(Integer timout, String url);

}
