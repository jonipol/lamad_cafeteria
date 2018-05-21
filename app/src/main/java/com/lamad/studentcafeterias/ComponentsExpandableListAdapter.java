package com.lamad.studentcafeterias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComponentsExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<Integer, List<Dish>> dataList;
    private List<String> weekdays;


    public ComponentsExpandableListAdapter(Context context, Restaurant data) {
        this.context = context;
        this.dataList = data.getMenu();
        weekdays = new ArrayList<>();
        weekdays.add(context.getString(R.string.monday));
        weekdays.add(context.getString(R.string.tuesday));
        weekdays.add(context.getString(R.string.wednesday));
        weekdays.add(context.getString(R.string.thursday));
        weekdays.add(context.getString(R.string.friday));
        weekdays.add(context.getString(R.string.saturday));
        weekdays.add(context.getString(R.string.sunday));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).get(childPosition);
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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menulist_item, null);
        }

        List<Dish> dishesForDay = dataList.get(groupPosition);

        TextView componentView = convertView.findViewById(R.id.dishComponent);
        StringBuilder stringBuilder = new StringBuilder();
        if (!dishesForDay.isEmpty())
            for (String item : dishesForDay.get(childPosition).getItems()) {
                stringBuilder.append(item + "\n");
            }
        else
            stringBuilder.append(context.getResources().getString(R.string.no_menu_available));

        componentView.setText(stringBuilder.toString());

        TextView priceView = convertView.findViewById(R.id.dishPrice);
        if (!dishesForDay.isEmpty())
            priceView.setText(dishesForDay.get(childPosition).getPrice());
        else
            priceView.setText("");

        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        if (dataList.get(groupPosition).size() == 0)
            return 1;
        return dataList.get(groupPosition).size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menulist_header, null);
        }

        TextView dayView = convertView.findViewById(R.id.menuListHeaderDay);
        dayView.setText(weekdays.get(groupPosition));

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
