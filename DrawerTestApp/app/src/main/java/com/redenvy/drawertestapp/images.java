package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.redenvy.drawertestapp.databinding.ActivityImagesBinding;

public class images extends AppCompatActivity {
    private ActivityImagesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        binding.planets.setOnClickListener(view -> binding.planets.setImageResource(R.drawable.bjit));
    }
}