package br.com.lucas.modules.usecases;

import br.com.lucas.modules.domain.Address;
import br.com.lucas.modules.dto.AddressRequest;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface SearchAddressUseCase {

    public Address execute(AddressRequest postalCode) throws IOException, InterruptedException, ApiException;

}
