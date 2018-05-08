package com.lamad.studentcafeterias;

import java.util.List;

public class Dish {
    private List<String> items;
    private double price;

    public Dish(List<String> items, double price) {
        this.items = items;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItem(List<String> items) {
        this.items = items;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
