package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.sharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String email,pass;
    public SharedPreferences sharedPreferences;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        binding.saveButton.setOnClickListener(this);
        binding.go.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.saveButton:
                email = binding.email.getText().toString();
                pass = binding.pass.getText().toString();
                if (email.isEmpty()){
                    binding.email.setError(getString(R.string.enteremail));
                }
                else if (pass.isEmpty()){
                    binding.pass.setError(getString(R.string.enterpass));
                }
                else{
                    saveData();
                }
                break;
            case R.id.go:
                startActivity(new Intent(MainActivity.this,SideActivity.class));
        }
    }
    public void saveData(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.EMAIL,email);
        editor.putString(Constant.PASS,pass);
        editor.apply();
        binding.email.setText("");
        binding.pass.setText("");
    }
}
