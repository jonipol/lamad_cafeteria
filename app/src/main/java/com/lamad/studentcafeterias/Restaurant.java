package com.lamad.studentcafeterias;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class Restaurant implements Serializable, Comparable{

    private String image;
    private String name;
    private double latitude;
    private double longitude;
    private String link;
    private String address;
    private HashMap<Integer, List<Dish>> menu;
    private double distance = -1;

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

    public Restaurant(String name, HashMap<Integer, List<Dish>> menus) {
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

    public Restaurant(String name, String address, String link, double latitude, double longitude, HashMap<Integer, List<Dish>> menus) {
        this.name = name;
        this.address = address;
        this.link = link;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menus;
    }

    public Restaurant(String name, String address, String link,  double latitude, double longitude, HashMap<Integer, List<Dish>> menus, String image) {
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

    public HashMap<Integer, List<Dish>> getMenu() {
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

    public void setMenu(HashMap<Integer, List<Dish>> menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return name + ": " + address + " (" + latitude + " | " + longitude + ") " + "\n" + menu;
    }

    @Override
    public boolean equals(Object o) {
        Restaurant restaurant = (Restaurant) o;
        return this.getName().toUpperCase().equals(restaurant.getName().toUpperCase());
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Restaurant restaurant = (Restaurant) o;
        return Double.compare(this.distance, restaurant.getDistance());
    }

    public static Comparator<Restaurant> RestaurantComparator = new Comparator<Restaurant>() {
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            return o1.compareTo(o2);
        }
    };
}
