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
    private Boolean opened;
    private Double rating;
    private String vicinity;
    private Category category;
    private Coordinates coordinates;
    private String distance;

    public Places(String name, Boolean opened, Double rating, String vicinity, String distance, Category category, Coordinates coordinates) {
        this.name = name;
        this.opened = opened;
        this.rating = rating;
        this.vicinity = vicinity;
        this.category = category;
        this.coordinates = coordinates;
        this.distance = distance;
    }

}
