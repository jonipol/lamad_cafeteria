package com.lamad.studentcafeterias;

import android.util.JsonReader;
import android.util.SparseArray;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public static List<Restaurant> readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            return readRestaurantArray(reader);
        } finally {
            reader.close();
        }
    }

    private static List<Restaurant> readRestaurantArray(JsonReader reader) throws IOException {
        List<Restaurant> restaurants = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            restaurants.add(readRestaurant(reader));
        }
        reader.endArray();
        return restaurants;
    }

    private static Restaurant readRestaurant(JsonReader reader) throws IOException {
        String name = null;
        SparseArray<List<Dish>> menus = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String id = reader.nextName();
            if (id.equals("restaurantName"))
                name = reader.nextString();
            else if (id.equals("menus"))
                menus = readMenuOfWeek(reader);
            else
                reader.skipValue();
        }
        reader.endObject();
        return new Restaurant(name, menus);
    }

    private static SparseArray<List<Dish>> readMenuOfWeek(JsonReader reader) throws IOException {
        SparseArray<List<Dish>> menus = new SparseArray<>();
        int numberOfDay = 0;

        reader.beginArray();
        while (reader.hasNext()) {
            menus.append(numberOfDay, readMenuOFDay(reader));
            numberOfDay++;
        }
        reader.endArray();
        return menus;
    }

    private static List<Dish> readMenuOFDay(JsonReader reader) throws IOException {
        List<Dish> listOfDishes = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            listOfDishes.add(readMenu(reader));
        }
        reader.endArray();
        return listOfDishes;
    }

    private static Dish readMenu(JsonReader reader) throws IOException {
        List<String> components = null;
        String price = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String id = reader.nextName();
            if (id.equals("fin"))
                components = readComponents(reader);
            else if (id.equals("price"))
                price = reader.nextString();
            else
                reader.skipValue();
        }
        reader.endObject();
        return new Dish(components, price);
    }

    private static List<String> readComponents(JsonReader reader) throws IOException {
        List<String> components = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            components.add(reader.nextString());
        }
        reader.endArray();
        return components;
    }
}
