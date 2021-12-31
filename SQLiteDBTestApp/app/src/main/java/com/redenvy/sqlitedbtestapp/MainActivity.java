package com.redenvy.sqlitedbtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.redenvy.sqlitedbtestapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding bind;
    private SQLiteDB sqLiteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.addButton.setOnClickListener(this);
        bind.showButton.setOnClickListener(this);
        sqLiteDB = new SQLiteDB(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addButton:
                sqLiteDB.insertData(bind.carName.getText().toString(),bind.brandName.getText().toString(),bind.patch.getText().toString());
                break;
            case R.id.showButton:
                sqLiteDB.getData();
                break;
        }
    }
}