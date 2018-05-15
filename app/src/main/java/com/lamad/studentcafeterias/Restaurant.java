package com.lamad.studentcafeterias;

import android.graphics.Bitmap;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class Restaurant implements Serializable {

    private String image;
    private String name;
    private double latitude;
    private double longitude;
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

    public Restaurant(String name, String address, String link, double latitude, double longitude, String image) {
        this.name = name;
        this.address = address;
        this.link = link;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public Restaurant(String name, String address, String link, double latitude, double longitude, SparseArray<List<Dish>> menus) {
        this.name = name;
        this.address = address;
        this.link = link;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menus;
    }

    public Restaurant(String name, String address, String link,  double latitude, double longitude, SparseArray<List<Dish>> menus, String image) {
        this.name = name;
        this.address = address;
        this.link = link;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menus;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude() {
        return longitude;
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
        return name + ": " + address + " (" + latitude + " | " + longitude + ") " + "\n" + menu;
    }
}
