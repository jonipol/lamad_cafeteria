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

import org.json.JSONObject;

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
        dummyData();

        listAdapter = new ExpandableListAdapter(this.getContext(), dataList);
        expandableListView.setAdapter(listAdapter);


        //button = view.findViewById(R.id.showOnMapButton);
        //button.setOnClickListener(showOnMapListener);
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

    private void fetchData() {


        try {
            JSONObject reader = new JSONObject("in");
        } catch (Exception e) { // TODO: Specify ioexception?
            Log.e(TAG, "Error in reading JSON");
        }
    }

    private void parseData(JSONObject json) {



    }

}
