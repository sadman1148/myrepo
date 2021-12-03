package com.redenvy.knowyourbuttons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SwitchMaterial trigger;
    private CheckBox checkBox;
    private RadioButton radioButton;
    private Button normal, outlined, text, toggle;
    private FloatingActionButton fab;
    private ImageButton imgText;
    static int on = 1;
    static int onT = 1;
    static int detail = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaching id
        normal = findViewById(R.id.normal);
        outlined = findViewById(R.id.outlined);
        text = findViewById(R.id.text);
        trigger = findViewById(R.id.trigger);
        imgText = findViewById(R.id.imageButton);
        toggle = findViewById(R.id.toggleButton);
        checkBox = findViewById(R.id.checkBox);
        radioButton = findViewById(R.id.radioButton);
        fab = findViewById(R.id.floatingActionButton);
        
        // Calling onClick
        normal.setOnClickListener(this);
        outlined.setOnClickListener(this);
        text.setOnClickListener(this);
        trigger.setOnClickListener(this);
        imgText.setOnClickListener(this);
        toggle.setOnClickListener(this);
        checkBox.setOnClickListener(this);
        radioButton.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                Toast.makeText(this, "Normal Button (Raised)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.outlined:
                Toast.makeText(this, "Outlined Button (Raised)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text:
                Toast.makeText(this, "Text Button (Flat)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.trigger:
                if (on==1){
                    Toast.makeText(this, "Switch turned ON", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Switch turned OFF", Toast.LENGTH_SHORT).show();
                }
                on = on * -1;
                break;
            case R.id.imageButton:
                Toast.makeText(this, "Image Button (Raised)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toggleButton:
                if (onT==1){
                    Toast.makeText(this, "Toggled ON", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Toggled OFF", Toast.LENGTH_SHORT).show();
                }
                onT = onT * -1;
                break;
            case R.id.checkBox:
                if (checkBox.isChecked()){
                    Toast.makeText(this, "Checkbox Checked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Checkbox Unchecked", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.radioButton:
                if (radioButton.isChecked() && detail==0){
                    Toast.makeText(this, "Radio selected", Toast.LENGTH_SHORT).show();
                    detail = 1;
                }else{
                    Toast.makeText(this, "Already selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.floatingActionButton:
                    Toast.makeText(this, "Button Clicked (FAB)", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}