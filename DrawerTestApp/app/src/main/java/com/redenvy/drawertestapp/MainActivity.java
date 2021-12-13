package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.redenvy.drawertestapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ActivityMainBinding binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        binder.popup.setOnClickListener(this);
        binder.drawer.setOnClickListener(this);
        binder.img.setOnClickListener(this);
        binder.tabs.setOnClickListener(this);
        binder.trans.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.popup:
                startActivity(new Intent(MainActivity.this, Popup.class));
                break;
            case R.id.drawer:
                startActivity(new Intent(MainActivity.this, drawer.class));
                break;
            case R.id.img:
                startActivity(new Intent(MainActivity.this, images.class));
                break;
            case R.id.tabs:
                startActivity(new Intent(MainActivity.this, tabs.class));
                break;
            case R.id.trans:
                startActivity(new Intent(MainActivity.this, transition.class));
                break;
            default:
                break;
        }
    }
}