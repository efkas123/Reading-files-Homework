package model;

import java.util.List;

public class Dish {
    private List<String> ingredients;
    private Integer priceInRubles;

    public List<String> getIngredients() {
        return ingredients;
    }

    public Integer getPriceInRubles() {
        return priceInRubles;
    }

    public Dish(){}
}
