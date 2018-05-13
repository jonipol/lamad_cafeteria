package com.lamad.studentcafeterias;

        import android.content.Context;
        import android.util.Log;
        import android.util.SparseArray;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

public class ComponentsExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private SparseArray<List<Dish>> dataList;
    private List<String> weekdays;


    public ComponentsExpandableListAdapter(Context context, Restaurant data) {
        this.context = context;
        this.dataList = data.getMenu();
        Log.v("CompExAdapter", "dataListComp: " +  getGroupCount());
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

        TextView componentView = convertView.findViewById(R.id.dishComponent);
        componentView.setText("TEST");

        TextView priceView = convertView.findViewById(R.id.dishPrice);
        priceView.setText("PRICE");

        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
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
