package com.example.test;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText f,l;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.textView);
        f = findViewById(R.id.fn);
        l = findViewById(R.id.ln);
    }

    public int convert(String x){
        Boolean pos = true;
        if(x.charAt(0) == '-'){
            x.substring(1); // here
            pos = false;
        }
        char [] a = x.toCharArray();

        Log.d(TAG, "first character in string: "+a[0]);

        int v = (int)a[a.length-1]-48;
        for(int i=a.length-2; i>=0; i--){
             int c = (int)a[i]-48 * 10; // here
            v += c;
        }

        Log.e(TAG, "value of V: "+v);

        if(!pos){
            v = -1 * v;
        }
        return v;
    }

    public void add(View view){
        int x = convert(f.getText().toString()) + convert(l.getText().toString());
        t.setText("Summation: "+x);
    }

    public void sub(View view){
        int x = convert(f.getText().toString()) - convert(l.getText().toString());
        t.setText("Difference: "+x);
    }

    public void mul(View view){
        int x = convert(f.getText().toString()) * convert(l.getText().toString());
        t.setText("Multiple: "+x);
    }

}