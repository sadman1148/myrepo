package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    TextView t = findViewById(R.id.textView);
    EditText f = findViewById(R.id.fn);
    EditText l = findViewById(R.id.ln);

    public int convert(String x){
        char [] a = x.toCharArray();
        for(int i=0; i<a.length; i++){

        }
    }

    public void add(View view){
        int x = f.getText() + l.getText();
        t.setText("Sumation: "+x);
    }
}