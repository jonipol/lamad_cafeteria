package com.lamad.studentcafeterias;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONParser {
    private static final String TAG = "JSONPARSER";

    public static List<Restaurant> readRestaurantJson(JSONArray jsonArray) throws JSONException {
        List<Restaurant> restaurantList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("cafeteria");
            double latitude = jsonObject.getDouble("latitude");
            double longitude = jsonObject.getDouble("longtitude");
            String webpage = jsonObject.getString("webpage");
            String address = jsonObject.getString("address");
            String image = jsonObject.getString("image_file");
            restaurantList.add(new Restaurant(name, address, webpage, latitude, longitude, image));
        }
        return restaurantList;
    }

    public static void readDishJson(JSONArray jsonArray) throws JSONException {
        List<Restaurant> dataList = RestaurantListFragment.dataList;
        for (int i = 0; i < jsonArray.length(); i++) {
            Restaurant restaurant = null;
            HashMap<Integer, List<Dish>> restaurantMenus = new HashMap<>();
            JSONObject restaurantJsonObject = jsonArray.getJSONObject(i);
            String restaurantName = restaurantJsonObject.getString("restaurantName");
            // Because Carelia has that "opiskelijaravintola" infront of its name.
            // TODO: Better solution to Carelias name. This solution breaks all restaurant
            // TODO: names that have two or more words in it.
            if (restaurantName.split(" ").length > 1) {
                restaurantName = restaurantName.split(" ")[1];
            }
            // Check if the restaurant can be found from database
            for (int j = 0; j < dataList.size(); j++) {
                if (dataList.get(j).getName().equals(restaurantName))
                    restaurant = dataList.get(j);
            }

            if (restaurant != null) {     // Restaurant is found from database
                JSONArray weeklyMenuJsonArray = restaurantJsonObject.getJSONArray("menus");
                for (int k = 0; k < weeklyMenuJsonArray.length(); k++) {
                    JSONArray dailyMenu = weeklyMenuJsonArray.getJSONArray(k);
                    List<Dish> dishList = new ArrayList<>();
                    for (int l = 0; l < dailyMenu.length(); l++) {
                        JSONObject singleDish = dailyMenu.getJSONObject(l);
                        JSONArray componentsJson = singleDish.getJSONArray("fin");
                        List<String> componentList = new ArrayList<>();
                        for (int m = 0; m < componentsJson.length(); m++) {
                            componentList.add(componentsJson.getString(m));
                        }
                        dishList.add(new Dish(componentList, singleDish.getString("price")));
                    }
                    restaurantMenus.put(k, dishList);
                }
            } else
                Log.wtf(TAG, "Restaurant " + restaurantName + " is missing from cafeterias table");

            restaurant.setMenu(restaurantMenus);
        }
        RestaurantListFragment.dataList = dataList;
    }
}