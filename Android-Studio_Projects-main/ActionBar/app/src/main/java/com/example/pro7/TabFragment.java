package com.example.pro7;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    public static TabFragment newInstance(int position) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        int position = getArguments().getInt(ARG_POSITION);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView = rootView.findViewById(R.id.tab_text);
        ImageView imageView = rootView.findViewById(R.id.tab_image);

        switch (position) {
            case 0:
                textView.setText("Tab 1 Content");
//                imageView.setImageResource(R.drawable.ic_tab1_image);
                break;
            case 1:
                textView.setText("Tab 2 Content");
//                imageView.setImageResource(R.drawable.ic_tab2_image);
                break;
            case 2:
                textView.setText("Tab 3 Content");
//                imageView.setImageResource(R.drawable.ic_tab3_image);
                break;
        }

        return rootView;
    }
}
