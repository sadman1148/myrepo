package com.redenvy.bindertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.redenvy.bindertest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());

        binder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binder.textView.setText("It Worked..");
            }
        });
    }
}