package com.lamad.studentcafeterias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Restaurant> dataList;


    public ExpandableListAdapter(Context context, List<Restaurant> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    /*
        Gets the restaurant object. If the restaurant tab is changed to consist of
        multiple children this and getGroup methods have to be changed.
     */
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

        Restaurant restaurant = (Restaurant) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView locationTextView = convertView.findViewById(R.id.listLocation);
        locationTextView.setText(restaurant.getAddress());

        TextView linkTextView = convertView.findViewById(R.id.listLink);
        linkTextView.setText(restaurant.getLink());

        /* Handle populating the menu */




        return convertView;
    }

    /*
        If restaurants are chosen to be shown on multiple levels eg. Name -> address, link, menu -> monday tuesday..
        Change this method.
     */
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
