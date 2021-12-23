package com.redenvy.thegoodservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.redenvy.thegoodservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binder;
    private boolean letPause = false;
    private boolean letStop = false;
    private boolean letPlay = true;
    private boolean letResume = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        binder.playButton.setOnClickListener(this);
        binder.pauseButton.setOnClickListener(this);
        binder.stopButton.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play_button:
                if(letPlay && !letResume){
                    startService(new Intent(getApplicationContext(),MusicService.class));
                    letPause = true;
                    letStop = true;
                    letPlay = false;
                    letResume = false;
                    Toast.makeText(this, "Music Started", Toast.LENGTH_SHORT).show();
                }
                else if(letPlay && letResume){
                    startService(new Intent(getApplicationContext(),MusicService.class).putExtra("Resume",true));
                    letPause = true;
                    letStop = true;
                    letResume = false;
                    letPlay = false;
                    Toast.makeText(this, "Music Resumed", Toast.LENGTH_SHORT).show();
                }
                binder.musicAnimationView.setVisibility(view.VISIBLE);
                break;
            case R.id.pause_button:
                if(letPause){
                    startService(new Intent(getApplicationContext(),MusicService.class).putExtra("Pause",true));
                    letPause = false;
                    letPlay = true;
                    letResume = true;
                    binder.musicAnimationView.setVisibility(view.GONE);
                    Toast.makeText(this, "Music Paused", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.stop_button:
                if(letStop){
                    stopService(new Intent(getApplicationContext(),MusicService.class));
                    letStop = false;
                    letPause = false;
                    letPlay = true;
                    binder.musicAnimationView.setVisibility(view.GONE);
                    Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}