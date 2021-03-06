package com.lamad.studentcafeterias;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class RestaurantListFragment extends Fragment {

    String TAG = "RestaurantListFragment";
    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    static List<Restaurant> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        expandableListView = view.findViewById(R.id.expendableRestaurantListView);
        listAdapter = new ExpandableRestaurantListAdapter(this.getContext(), dataList, getFragmentManager());
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    expandableListView.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });
        return view;
    }

    public static void parseCafeterias(JSONArray jsonArray) {
        try {
            List<Restaurant> restaurantList = (JSONParser.readRestaurantJson(jsonArray));
            for (Restaurant restaurant : restaurantList) {
                if (!dataList.contains(restaurant))
                    dataList.add(restaurant);
            }
            calculateLocations();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parseMenus(JSONArray jsonArray) {
        try {
            JSONParser.readDishJson(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void calculateLocations(){

        Handler handler = new Handler();

        Location location = SplashActivity.getLocation();
        if (location == null){
            return;
        }

        LocationCalculations.calculateDistanceToAllRestaurants(location);
        Runnable delayedSort = new Runnable() {
            @Override
            public void run() {
                Collections.sort(RestaurantListFragment.dataList, new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant o1, Restaurant o2) {
                        return o1.compareTo(o2);
                    }
                });
            }
        };
        handler.postDelayed(delayedSort, 1000);
    }
}
