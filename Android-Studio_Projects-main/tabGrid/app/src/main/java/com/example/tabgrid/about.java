package com.example.tabgrid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class about extends Fragment {

    private GridView gridView;
    private String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        gridView = rootView.findViewById(R.id.gridView);
        GridAdapter adapter = new GridAdapter(getContext(), items);
        gridView.setAdapter(adapter);

        return rootView;
    }
}
