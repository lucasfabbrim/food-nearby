package br.com.lucas.modules.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(){
        super("No location found for the given postal code.");
    }
}
