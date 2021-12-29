package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SideActivity extends AppCompatActivity {
    public SharedPreferences sharedPreferences;
    private EditText emailET,passET;
    private String email,pass;
    private Button delete,load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        sharedPreferences = getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        emailET = findViewById(R.id.email);
        passET = findViewById(R.id.pass);
        delete = findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Constant.PASS);
                editor.commit();
                emailET.setText("");
                passET.setText("");
            }
        });
        load = findViewById(R.id.loadButton);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }
    public void getData(){
        email = sharedPreferences.getString(Constant.EMAIL,"");
        pass = sharedPreferences.getString(Constant.PASS, "");
        Log.e(getString(R.string.TAG), "Email: "+email);
        Log.e(getString(R.string.TAG), "Pass: "+pass);
        emailET.setText(email);
        passET.setText(pass);
    }
}