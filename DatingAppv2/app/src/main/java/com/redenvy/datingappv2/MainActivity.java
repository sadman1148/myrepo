package com.redenvy.datingappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.redenvy.datingappv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binder;
    private int want = 0, done = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        binder.photo.setOnClickListener(this);
        binder.menuOrder.setOnClickListener(this);
        binder.menuLike.setOnClickListener(this);
        binder.menuComment.setOnClickListener(this);
        binder.menuDL.setOnClickListener(this);
        binder.menuEdit.setOnClickListener(this);
        binder.navHome.setOnClickListener(this);
        binder.navProf.setOnClickListener(this);
        binder.navTips.setOnClickListener(this);
        binder.settings.setOnClickListener(this);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.photo:
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                break;
            case R.id.menuOrder:
                want++;
                binder.wantdoneText.setText(want+" want | "+done+" done");
                break;
            case R.id.menuLike:
                done++;
                binder.wantdoneText.setText(want+" want | "+done+" done");
                break;
            case R.id.menuComment:
                Toast.makeText(this, "Commented", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuDL:
                Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuEdit:
                Toast.makeText(this, "Edited", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navTips:
                Toast.makeText(this, "Navigate to Tips", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navHome:
                Toast.makeText(this, "Navigate to Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navProf:
                Toast.makeText(this, "Navigate to Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,binder.settings);
                popupMenu.getMenuInflater().inflate(R.menu.popup_items, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String out = item.getTitle().toString()+" selected";
                        Toast.makeText(MainActivity.this, out, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
                break;
        }
    }
}