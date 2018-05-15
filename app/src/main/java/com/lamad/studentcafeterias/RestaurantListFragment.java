package com.lamad.studentcafeterias;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RestaurantListFragment extends Fragment {

    String TAG = "RestaurantListFragment";
    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    static List<Restaurant> dataList = new ArrayList<>();
    static List<Restaurant> dishList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        expandableListView = view.findViewById(R.id.expendableRestaurantListView);
        Log.v(TAG, "Starting population");
        populateDataList();

//        final RequestFromServer requestFromServer = new RequestFromServer();
//        Runnable runnable = new Runnable() {
//            @Override//            public void run() {
//                try{
//                    requestFromServer.getCafeterias();
//                    requestFromServer.getMenus();
//                }
//                catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        };
//        Handler h = new Handler();
//        h.postDelayed(runnable, 1000);  // Timeout for getting answer from the server


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


    private void populateDataList() {
        try {
            Log.v(TAG, " " + LocationCalculations.calculateDistance(62.556744, 29.158998,62.602239, 29.756036)); // TEST
            // TODO: Make comparison to be more reliable.
            for (int i = 0; i < dataList.size(); i++) {
                dataList.get(i).setMenu(dishList.get(i).getMenu());
            }
            // this loop works only if the restaurants are in the same order in both lists.
//            for (int i = 0; i < dataList.size(); i++) {
//                if (dataList.get(i).equals(listOfRestaurants.get(i)))   // Checks if restaurants have the same name
//                    dataList.get(i).setMenu(listOfRestaurants.get(i).getMenu());   // TODO: Simplify
//            }
//            Log.v(TAG, "Restaurant 1 " + dataList.get(0).getMenu());
//            Log.v(TAG, "DataList Size: " + dataList.size());
//            Log.v(TAG, "Dish :" + dataList.get(0).getMenu().get(1).get(0).toString());

        } catch (Exception e) {     // TODO: Better catches
            Log.e(TAG, "JSON ERROR", e);
        }
    }

    public static void parseCafeterias(JSONArray jsonArray) {
        try {
            dataList.addAll(JSONParser.readRestaurantJson(jsonArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //public static void parseMenus(JSONArray jsonArray) {
    //    try {

            //dishList.addAll(JSONParser.readDishJson(jsonArray));
    //    } catch (JSONException e) {
    //        e.printStackTrace();
    //    }
    //}
}
