package com.redenvy.broadcasttestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    AirPlaneModeChangeReceiver airplaneModeChangeReceiver = new AirPlaneModeChangeReceiver();
    CustomBroadcastReceiver customBroadcastReceiver = new CustomBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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