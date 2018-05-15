package com.lamad.studentcafeterias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class ComponentsListFragment extends Fragment {

    ExpandableListView expandableListView;
    ComponentsExpandableListAdapter listAdapter;
    Restaurant restaurant;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        Bundle bundle = this.getArguments();
        restaurant = (Restaurant) bundle.getSerializable("restaurant");
        expandableListView = view.findViewById(R.id.expendableRestaurantListView);
        listAdapter = new ComponentsExpandableListAdapter(this.getContext(), restaurant);
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

}
