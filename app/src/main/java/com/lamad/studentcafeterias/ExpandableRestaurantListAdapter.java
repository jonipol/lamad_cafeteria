package com.lamad.studentcafeterias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpandableRestaurantListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Restaurant> dataList;
    private ExpandableListView componentsExpView;
    private ComponentsExpandableListAdapter listAdapter;
    private FragmentManager fragmentManager;


    public ExpandableRestaurantListAdapter(Context context, List<Restaurant> dataList, FragmentManager fragmentManager) {
        this.context = context;
        this.dataList = dataList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.dataList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.dataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Restaurant restaurant = (Restaurant) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView locationTextView = convertView.findViewById(R.id.listLocation);
        locationTextView.setText(restaurant.getAddress());

        TextView linkTextView = convertView.findViewById(R.id.listLink);
        linkTextView.setText(restaurant.getLink());

        Button viewMenuButton = convertView.findViewById(R.id.viewMenuButton);
        viewMenuButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
//                  Intent intent = new Intent(context, MenuListActivity.class);
//                  intent.putExtra("restaurant", restaurant);
//                  context.startActivity(intent);
                  Fragment newFragment = new ComponentsListFragment();
                  Bundle bundle = new Bundle();
                  bundle.putSerializable("restaurant", restaurant);
                  newFragment.setArguments(bundle);
                  FragmentTransaction transaction = fragmentManager.beginTransaction();
                  transaction.replace(R.id.fragmentPlaceHolder, newFragment);
                  transaction.addToBackStack(null);
                  transaction.commit();
              }
          });



        /* Handle populating the menu */
//        componentsExpView = convertView.findViewById(R.id.componentsExpandableListView);
//        componentsExpView.setAdapter(new ComponentsExpandableListAdapter(context, restaurant));
//        convertView.findViewById(R.id.componentsExpandableListView);
//        // Listview Group expanded listener
//        componentsExpView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(context,
//                        "Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        Restaurant restaurant = (Restaurant) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_header, null);
        }

        ImageView imageView = convertView
                .findViewById(R.id.headerImageView);
        //imageView.setImageBitmap(restaurant.getImage()); // Commented for testing purposes
        imageView.setImageResource(R.mipmap.ic_launcher);

        TextView nameView = convertView
                .findViewById(R.id.headerLabel);
        nameView.setText(restaurant.getName());

        TextView distanceView = convertView
                .findViewById(R.id.headerDistance);
        distanceView.setText("500m"); // TODO: Calculation of the distance


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
