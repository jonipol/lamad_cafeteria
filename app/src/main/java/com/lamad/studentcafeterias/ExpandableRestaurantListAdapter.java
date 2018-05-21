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
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ExpandableRestaurantListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Restaurant> dataList;
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
        locationTextView.setText(String.format(context.getString(R.string.address_field),restaurant.getAddress()));

        TextView linkTextView = convertView.findViewById(R.id.listLink);
        linkTextView.setText(String.format(context.getString(R.string.link_field), restaurant.getLink()));
        // TODO: Change the text to a icon button

        Button viewMenuButton = convertView.findViewById(R.id.viewMenuButton);
        viewMenuButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
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

        Button mapButton = convertView.findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("RESTAURANT_NAME", restaurant.getName());
                intent.putExtra("LATITUDE", restaurant.getLatitude());
                intent.putExtra("LONGITUDE", restaurant.getLongitude());
                context.startActivity(intent);
            }});

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
        String imageInString = restaurant.getImage();
        String imageName = imageInString.substring(0, imageInString.length() - 4); //Removing the .jpg ending
        imageView.setImageResource(context.getResources().getIdentifier(imageName, "drawable", context.getPackageName()));

        TextView nameView = convertView
                .findViewById(R.id.headerLabel);
        nameView.setText(restaurant.getName());

        TextView distanceView = convertView
                .findViewById(R.id.headerDistance);
        distanceView.setText(String.valueOf(restaurant.getDistance())); // TODO: Calculation of the distance

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
