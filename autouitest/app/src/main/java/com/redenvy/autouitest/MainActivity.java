package com.redenvy.autouitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.redenvy.autouitest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.button.setOnClickListener(this);
        bind.checkBox.setOnClickListener(this);
        bind.switcher.setOnClickListener(this);
        bind.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bind.seeker.setText(String.valueOf(progress));
                float prog = (float) progress/100;
                bind.linlay2.setAlpha(prog);
                if(progress>19 && progress<81){
                    bind.mood.setImageResource(R.drawable.smile);
                }
                else if(progress>80){
                    bind.mood.setImageResource(R.drawable.sweat);
                }
                else{
                    bind.mood.setImageResource(R.drawable.freeze);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                bind.passtxt.setText(bind.pass.getText().toString());
                break;
            case R.id.checkBox:
                if (bind.checkBox.isChecked()) {
                    bind.checkBox.setText(R.string.checked);
                } else {
                    bind.checkBox.setText(R.string.unchecked);
                }
                break;
            case R.id.switcher:
                if (bind.switcher.isChecked()) {
                    bind.switcher.setText(R.string.on);
                } else {
                    bind.switcher.setText(R.string.off);
                }
                break;
        }
    }
}