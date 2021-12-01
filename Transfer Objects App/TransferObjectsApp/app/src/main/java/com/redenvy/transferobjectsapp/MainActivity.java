package com.redenvy.transferobjectsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void join(View view){
        EditText n = findViewById(R.id.name);
        EditText id = findViewById(R.id.id);
        EditText ad = findViewById(R.id.address);
        EditText ph = findViewById(R.id.phone);
        EditText pl = findViewById(R.id.platform);

        int idn =Integer.parseInt(id.getText().toString());

        user u = new user(n.getText().toString(),
                idn,
                ad.getText().toString(),
                ph.getText().toString(),
                pl.getText().toString());

        Intent i = new Intent(this, SecondActivity.class);
        String data = new Gson().toJson(u);
        i.putExtra("data",data);
        Toast.makeText(this, "New User Added", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}