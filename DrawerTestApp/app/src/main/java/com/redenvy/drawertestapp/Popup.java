package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.redenvy.drawertestapp.databinding.ActivityMainBinding;
import com.redenvy.drawertestapp.databinding.ActivityPopupBinding;

public class Popup extends AppCompatActivity implements View.OnClickListener {
    private ActivityPopupBinding binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityPopupBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        binder.imageButton.setOnClickListener(this);
        binder.destroy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                PopupMenu popup = new PopupMenu(Popup.this, binder.imageButton);
                popup.getMenuInflater().inflate(R.menu.popup_items, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        binder.languageText.setText(item.getTitle().toString());
                        return true;
                    }
                });
                popup.show();
                break;
            case R.id.destroy:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}