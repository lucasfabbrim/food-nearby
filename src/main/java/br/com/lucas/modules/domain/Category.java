package br.com.lucas.modules.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class Category {

    private String[] categorys;

    public Category(String[] categorys) {
        this.categorys = categorys;
    }

}
