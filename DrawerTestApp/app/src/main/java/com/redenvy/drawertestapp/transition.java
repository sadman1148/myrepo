package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.redenvy.drawertestapp.databinding.ActivityTransitionBinding;

public class transition extends AppCompatActivity implements View.OnClickListener {
    private ActivityTransitionBinding binding;
    private ActivityOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransitionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.linlay1.setOnClickListener(this);
        binding.linlay2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.linlay1:
                animate(binding.transPp1,"profilePic1");
                startActivity(new Intent(transition.this,sadman_trans.class),options.toBundle());
                break;
            case R.id.linlay2:
                animate(binding.transPp2,"profilePic2");
                startActivity(new Intent(transition.this,eftekhar_trans.class),options.toBundle());
                break;
        }
    }

    public void animate(View v, String sharedEleName){
        options = ActivityOptions.makeSceneTransitionAnimation(transition.this,v,sharedEleName);
    }
}