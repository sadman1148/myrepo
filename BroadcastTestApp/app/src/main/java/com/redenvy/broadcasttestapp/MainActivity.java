package com.redenvy.broadcasttestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AirPlaneModeChangeReceiver airplaneModeChangeReceiver = new AirPlaneModeChangeReceiver();
    CustomBroadcastReceiver customBroadcastReceiver = new CustomBroadcastReceiver();
    TextView t;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.textView);
        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(R.string.title1);

                // delaying 10 seconds here
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    t.setText(R.string.title2);
                    // sending broadcast after the 10 seconds
                    registerReceiver(customBroadcastReceiver, new IntentFilter(constant.CUSTOM_INTENT));
                    sendBroadcast(new Intent().setAction(constant.CUSTOM_INTENT));
                    }, 10000);
            }
        });
    }
    protected void onStart(){
        super.onStart();
        //register
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver,filter);
    }
    protected void onStop(){
        super.onStop();
        //unregister
        unregisterReceiver(airplaneModeChangeReceiver);
        unregisterReceiver(customBroadcastReceiver);
    }
}