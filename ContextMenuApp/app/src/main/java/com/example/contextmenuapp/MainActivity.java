package com.example.contextmenuapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rootLayout =findViewById(R.id.rootLayout);
        TextView textView=findViewById(R.id.textView);

        registerForContextMenu(rootLayout);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Choose a color");
        menu.add(0, v.getId(),0,"Red");
        menu.add(0, v.getId(), 0, "Green");
        menu.add(0, v.getId(), 0, "Blue");
    }
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("Red")){
            rootLayout.setBackgroundColor(Color.RED);
        } else if (item.getTitle().equals("Green")) {
            rootLayout.setBackgroundColor(Color.GREEN);
        } else if (item.getTitle().equals("Blue")) {
            rootLayout.setBackgroundColor(Color.BLUE);
        }
        return true;

    }

}