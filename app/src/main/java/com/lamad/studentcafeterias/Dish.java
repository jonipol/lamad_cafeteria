package com.lamad.studentcafeterias;

import java.io.Serializable;
import java.util.List;

public class Dish implements Serializable{
    private List<String> items;
    private String price;

    public Dish(List<String> items, String price) {
        this.items = items;
        this.price = price;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItem(List<String> items) {
        this.items = items;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        //String components = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : items) {
            //components += item + "\n";
            stringBuilder.append(item);
        }
        return stringBuilder.toString();
    }
}
