package com.example.contactslist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] contacts = {"Sreekesh","Vishnu","Sharon","Sarath","Manu","Jees","Sam","Deepak","Varghese","Ridha","Sneha","Sreekumar"};
        ListView contactListView = findViewById(R.id.contactListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contacts);
        contactListView.setAdapter(adapter);
    }
}
