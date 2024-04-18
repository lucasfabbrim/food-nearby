package br.com.lucas.modules.domain;

public class Category {

    private String url;
    private CategoryType categoryType;

    public Category(String url, CategoryType categoryType) {
        this.url = url;
        this.categoryType = categoryType;
    }

    enum CategoryType {
        MARKET,
        BAKERY,
        RESTAURANT,
        FOOD;
    }

}
