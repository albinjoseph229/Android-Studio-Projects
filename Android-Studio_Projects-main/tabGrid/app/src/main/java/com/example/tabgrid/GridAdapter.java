package com.example.tabgrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private String[] items;

    public GridAdapter(Context context, String[] items) {
        this.mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.grid_item, null);

            TextView textView = gridView.findViewById(R.id.textView);
            textView.setText(items[position]);
        } else {
            gridView = convertView;
        }

        return gridView;
    }
}
