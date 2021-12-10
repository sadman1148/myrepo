package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.redenvy.drawertestapp.databinding.ActivityImagesBinding;

public class images extends AppCompatActivity {
    private ActivityImagesBinding binding;
    private Boolean isTriggered = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.planets.setOnClickListener(view -> {
            if (isTriggered) {
                binding.planets.setImageResource(R.drawable.bjit);
            }
            else {
                binding.planets.setImageResource(R.drawable.planets);
            }
            isTriggered = !isTriggered;
        });
        binding.url.setOnClickListener(view -> Glide.with(images.this).load("https://images.wallpapersden.com/image/download/purple-sunrise-4k-vaporwave_bGplZmiUmZqaraWkpJRmbmdlrWZlbWU.jpg").into(binding.url));
    }
}