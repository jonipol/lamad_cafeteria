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

import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RestaurantListFragment extends Fragment {

    String TAG = "RestaurantListFragment";
    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    List<Restaurant> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        expandableListView = view.findViewById(R.id.expendableRestaurantListView);
        populateDataList();

        final RequestFromServer requestFromServer = new RequestFromServer();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    requestFromServer.getCafeterias();
                    requestFromServer.getMenus();
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        Handler h = new Handler();
        h.postDelayed(runnable, 1000);  // Timeout for getting answer from the server


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
        // TODO: Get the data from server
        dataList = new ArrayList<>();
        try {
            InputStream inputStream = getContext().getAssets().open("studentCafeteriaMenus.txt");
            // TODO: Check if the restaurant is already in the memory.
            // Create restaurants
            Restaurant aura = new Restaurant("Aura", "address", "link");
            Restaurant carelia = new Restaurant("Opiskelijaravintola Carelia", "address", "link");
            Restaurant futura = new Restaurant("Futura", "address", "link");
            Restaurant tori36 = new Restaurant("Tori36", "address", "link");
            Restaurant louhi = new Restaurant("louhi", "address", "link");
            dataList.add(aura);
            dataList.add(carelia);
            dataList.add(futura);
            dataList.add(tori36);
            dataList.add(louhi);
            // Get menus
            List<Restaurant> listOfRestaurants = JSONParser.readJsonStream(inputStream);
            // TODO: Make comparison to be more reliable. Maybe with sorting or something
            // this loop works only if the restaurants are in the same order in both lists.
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).equals(listOfRestaurants.get(i)))   // Checks if restaurants have the same name
                    dataList.get(i).setMenu(listOfRestaurants.get(i).getMenu());   // TODO: Simplify
            }
//            Log.v(TAG, "Restaurant 1 " + dataList.get(0).getMenu());
//            Log.v(TAG, "DataList Size: " + dataList.size());
//            Log.v(TAG, "Dish :" + dataList.get(0).getMenu().get(1).get(0).toString());

        } catch (Exception e) {     // TODO: Better catches
            Log.e(TAG, "JSON ERROR", e);
        }
    }
}
