package com.redenvy.transferobjectsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        user u = new Gson().fromJson(getIntent().getStringExtra("data"),user.class);
        TextView name = findViewById(R.id.n);
        TextView id = findViewById(R.id.i);
        TextView add = findViewById(R.id.ad);
        TextView ph = findViewById(R.id.ph);
        TextView pl = findViewById(R.id.pl);

        String n = "Name: "+u.name;
        String idn = "EID: "+u.id;
        String a = "Address: "+u.address;
        String p = "Number: "+u.phone;
        String plat = "Platform: "+u.platform;

        name.setText(n);
        id.setText(idn);
        add.setText(a);
        ph.setText(p);
        pl.setText(plat);
    }
}