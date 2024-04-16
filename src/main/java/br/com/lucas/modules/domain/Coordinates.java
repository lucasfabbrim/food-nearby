package br.com.lucas.modules.domain;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Data
public class Coordinates{
    private String type;
    private Double[] coordinates;

    private Coordinates(String type, Double[] coordinates){
        this.type = type;
        this.coordinates = coordinates;
    }
}