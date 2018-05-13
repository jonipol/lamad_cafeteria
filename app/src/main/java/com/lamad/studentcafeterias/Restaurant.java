package com.lamad.studentcafeterias;

import android.graphics.Bitmap;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class Restaurant implements Serializable{

    private Bitmap image;
    private String name;
    private LatLng location;
    private String link;
    private String address;
    private SparseArray<List<Dish>> menu; // SparseArray is more efficient than HashMap when pairing objects to Integers

    /**
     * For testing purposes
     * @param name
     * @param address
     * @param link
     */
    public Restaurant(String name, String address, String link) {
        this.name = name;
        this.address = address;
        this.link = link;
    }

    public Restaurant(String name, SparseArray<List<Dish>> menus) {
        this.name = name;
        this.menu = menus;
    }

    /**
     *
     * @param name
     * @param address
     * @param link
     * @param location
     * @param menus
     */
    public Restaurant(String name, String address, String link, LatLng location, SparseArray<List<Dish>> menus) {
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

    public SparseArray<List<Dish>> getMenu() {
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

    public void setMenu(SparseArray<List<Dish>> menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": " + address + " (" + location + ") " + "\n" + menu;
    }

    @Override
    public boolean equals(Object obj) {
        Restaurant castedObject = (Restaurant) obj;
        if (castedObject.getName().toUpperCase().equals(name.toUpperCase()))
            return true;
        else
            return false;
    }
}
