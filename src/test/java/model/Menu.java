package model;

import java.util.Map;

public class Menu{
    private String title;
    private String season;
    private Map<String, Dish> dishes;

    public String getTitle() {
        return title;
    }

    public String getSeason() {
        return season;
    }

    public Map<String, Dish> getDishes() {
        return dishes;
    }

    public Menu(){}
}

