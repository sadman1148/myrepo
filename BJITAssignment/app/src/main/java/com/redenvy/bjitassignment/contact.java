package com.redenvy.bjitassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
    }
    public void mailThisGuy(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + "sadman.alam@bjitgroup.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Automated Email");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi, this email was sent to you from an app that is under development.");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void callThisGuy(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "01994660589"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}