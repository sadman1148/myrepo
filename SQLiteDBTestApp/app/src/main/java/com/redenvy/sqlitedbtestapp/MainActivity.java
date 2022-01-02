package com.redenvy.sqlitedbtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.redenvy.sqlitedbtestapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding bind;
    private SQLiteDB sqLiteDB;
    private String [] localDataSet;
    private static final String TAG = "R3DENVY";
    private ArrayList <String> actualDataSet;
    private myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        sqLiteDB = new SQLiteDB(MainActivity.this);
        bind.addButton.setOnClickListener(this);
        bind.showButton.setOnClickListener(this);
        bind.wipe.setOnClickListener(this);
        actualDataSet = new ArrayList<String>();
        actualDataSet.add("Car_Brand_Patch");
        adapter = new myAdapter(actualDataSet,MainActivity.this);
        bind.recycler.setLayoutManager(new LinearLayoutManager(this));
        bind.recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addButton:
                sqLiteDB.insertData(
                        bind.carName.getText().toString(),
                        bind.brandName.getText().toString(),
                        bind.patch.getText().toString()
                );
                bind.carName.setText("");
                bind.brandName.setText("");
                bind.patch.setText("");
                break;
            case R.id.showButton:
                adapter.cleanSlate();
                actualDataSet.clear();
                actualDataSet.add("Car_Brand_Patch");
                String localData = sqLiteDB.getData();
                localDataSet = localData.split("\\.");
                Collections.addAll(actualDataSet, localDataSet);
                adapter.notifyDataSetChanged();
                break;
            case R.id.wipe:
                sqLiteDB.cleanSlate();
                adapter.cleanSlate();
                actualDataSet.clear();
                actualDataSet.add("Car_Brand_Patch");
                break;
        }
    }
}