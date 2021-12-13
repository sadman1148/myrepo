package com.redenvy.genericapp;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import android.os.Bundle;

import com.redenvy.genericapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textView1.setText(R.string.text1);
        binding.textView2.setText(R.string.text2);
        binding.textView3.setText(R.string.text3);
        binding.textView4.setText(R.string.text4);
        Glide.with(MainActivity.this).load("https://static.wikia.nocookie.net/thewire/images/5/5b/Poster.jpg/revision/latest/scale-to-width-down/680?cb=20200701211021").into(binding.imageView1);
        Glide.with(MainActivity.this).load("https://sciencefiction.com/wp-content/uploads/2017/06/Spider-Man-Homecoming-International-poster.jpg").into(binding.imageView2);
        Glide.with(MainActivity.this).load("https://images.squarespace-cdn.com/content/v1/5a90557e1137a6fdeeb6bb3e/1562325473324-OZCZ56ZKPFCFXZOR9EVZ/spider-man-far-from-home-poster-fury-mysterio-2.jpg?format=750w").into(binding.imageView3);
        Glide.with(MainActivity.this).load("https://terrigen-cdn-dev.marvel.com/content/prod/1x/snh_online_6072x9000_posed_01.jpg").into(binding.imageView4);
    }
}