package com.example.imagesdemo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    Button mybtn;
    VideoView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mybtn = findViewById(R.id.mybtn);
        v= findViewById(R.id.myvid);
        mybtn.setOnClickListener(v -> doThis());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void doThis(){
        MediaPlayer ring = MediaPlayer.create(MainActivity.this,R.raw.car);
        v.setVideoPath("android.resources://"+getPackageName()+"/"+R.raw.carvid);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(v);
        mediaController.setMediaPlayer(v);
        v.setMediaController(mediaController);
        String text = mybtn.getText().toString();
//        if(text.equals("Stop")){
//            mybtn.setText("Play");
//            ring.pause();
//            v.pause();
//        }
//        else {
//            ring.start();
//            mybtn.setText("Stop");
//            v.start();
//        }
            ring.start();
            mybtn.setText("Stop");
            v.start();
    }
}