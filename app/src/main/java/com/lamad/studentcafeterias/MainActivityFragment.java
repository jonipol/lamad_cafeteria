package com.lamad.studentcafeterias;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    String TAG = "MainActivityFragment";

    //Button button;
    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<Restaurant> dataList;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        expandableListView = view.findViewById(R.id.expandableListView);
        //dummyData();
        populateDataList();
        /*
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
        */




        listAdapter = new ExpandableListAdapter(this.getContext(), dataList);
        expandableListView.setAdapter(listAdapter);

        return view;
    }

    private View.OnClickListener showOnMapListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), MapsActivity.class);
            startActivity(intent);
        }
    };

    private void dummyData() {
        Restaurant r1 = new Restaurant("Louhi", "Länsikatu 16", "www...");
        Restaurant r2 = new Restaurant("Aura", "Somewhere", "www.aura...");

        dataList = new ArrayList<>();
        dataList.add(r1);
        dataList.add(r2);
    }

    private void populateDataList() {
        // TODO: Get the data from server
        dataList = new ArrayList<>();
        try {
            InputStream inputStream = getContext().getAssets().open("amicaMenu.txt");

            // TODO: Check if the restaurant is already in the memory.
            // Create restaurants
            Restaurant aura = new Restaurant("Aura", "address", "link");
            Restaurant carelia = new Restaurant("Carelia", "address", "link");
            Restaurant futura = new Restaurant("Futura", "address", "link");
            Restaurant tori36 = new Restaurant("Tori36", "address", "link");
            Restaurant louhi = new Restaurant("louhi", "address", "link");
            dataList.add(aura);
            dataList.add(carelia);
            dataList.add(futura);
            dataList.add(tori36);
            dataList.add(louhi);

            // Get menus
            List<Restaurant> listOFRestaurantMenus = JSONParser.readJsonStream(inputStream);
            // TODO: Make comparison to be more reliable. Maybe with sorting or something
            // this loop works only if the restaurants are in the same order in both lists.
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).equals(listOFRestaurantMenus.get(i)))
                    dataList.get(i).setMenu(listOFRestaurantMenus.get(i).getMenu());   // TODO: Simplify
            }

        } catch (Exception e) {     // TODO: Better catches
            e.printStackTrace();
            Log.e(TAG, getString(R.string.jsonObjError));
        }
    }


}
