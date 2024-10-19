package com.example.foodappui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodListAdapter extends ArrayAdapter<FoodItem> {

    private Context mContext;
    private List<FoodItem> mFoodList;

    public FoodListAdapter(Context context, List<FoodItem> foodList) {
        super(context, 0, foodList);
        mContext = context;
        mFoodList = foodList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_food, parent, false);
        }

        FoodItem currentFood = mFoodList.get(position);

        ImageView imageView = listItem.findViewById(R.id.foodImageView);
        imageView.setImageResource(currentFood.getImageResource());

        TextView nameTextView = listItem.findViewById(R.id.foodNameTextView);
        nameTextView.setText(currentFood.getName());

        TextView descriptionTextView = listItem.findViewById(R.id.foodDescriptionTextView);
        descriptionTextView.setText(currentFood.getDescription());

        return listItem;
    }
}

