package com.lamad.studentcafeterias;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;

public class Restaurant {

    private Bitmap image;
    private String name;
    private LatLng location;
    private String link;
    private String address;
    private HashMap<String, List<Dish>> menu; // <Day, components>

    public Restaurant(String name, String address, String link) {
        this.name = name;
        this.address = address;
        this.link = link;


    }

    public Restaurant(String name, String address, String link, LatLng location, HashMap<String, List<Dish>> menus) {
        this.name = name;
        this.address = address;
        this.link = link;
        this.location = location;
        this.menu = menus;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage() {
        return image;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public String getLink() {
        return link;
    }

    public HashMap<String, List<Dish>> getMenu() {
        return menu;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMenu(HashMap<String, List<Dish>> menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }
}
