package com.lamad.studentcafeterias;

import java.util.List;

public class Dish {
    private List<String> items;
    private float price;

    public Dish(List<String> items, float price) {
        this.items = items;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItem(List<String> items) {
        this.items = items;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
