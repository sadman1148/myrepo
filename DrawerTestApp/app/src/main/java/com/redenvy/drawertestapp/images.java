package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

import com.redenvy.drawertestapp.databinding.ActivityImagesBinding;

public class images extends AppCompatActivity {
    private ActivityImagesBinding binding;
    static Boolean x = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.planets.setOnClickListener(view -> {
            if (x) {
                binding.planets.setImageResource(R.drawable.bjit);
            }
            else {
                binding.planets.setImageResource(R.drawable.planets);
            }
            x = !x;
        });
    }
}