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
    private Pair [] p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransitionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.linlay1.setOnClickListener(this);
        binding.linlay2.setOnClickListener(this);
        binding.linlay3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.linlay1:
                p = new Pair[2];
                p[0] = new Pair<View,String>(binding.transPp1,"profilePic1");
                p[1] = new Pair<View,String>(binding.transNameText1,"name1");
                options = ActivityOptions.makeSceneTransitionAnimation(transition.this, p);
                startActivity(new Intent(transition.this,sadman_trans.class),options.toBundle());
                break;
            case R.id.linlay2:
                p = new Pair[2];
                p[0] = new Pair<View,String>(binding.transPp2,"profilePic2");
                p[1] = new Pair<View,String>(binding.transNameText2,"name2");
                options = ActivityOptions.makeSceneTransitionAnimation(transition.this, p);
                startActivity(new Intent(transition.this,eftekhar_trans.class),options.toBundle());
                break;
            case R.id.linlay3:
                p = new Pair[2];
                p[0] = new Pair<View,String>(binding.transPp3,"profilePic3");
                p[1] = new Pair<View,String>(binding.transNameText3,"name3");
                options = ActivityOptions.makeSceneTransitionAnimation(transition.this, p);
                startActivity(new Intent(transition.this,asef_trans.class),options.toBundle());
            default:
                break;
        }
    }
}