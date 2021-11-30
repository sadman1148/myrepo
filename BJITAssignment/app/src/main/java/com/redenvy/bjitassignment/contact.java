package com.redenvy.bjitassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
    }

    public void run(Intent intent){
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents", "Can't handle this!");
            Toast.makeText(this, "No Compatible App Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void mailThisGuy(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + "sadman.alam@bjitgroup.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Automated Email");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi, this email was sent to you from an app that is under development.");
        intent.putExtra(Intent.EXTRA_CC, new String[]{"sadman.alam.reshad@g.bracu.ac.bd"});
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "bjit.svg");
        if (!file.exists() && !file.canRead()) {
            Log.e("ImplicitIntents", "File is not in the downloads folder");
        }
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        run(intent);
    }
    public void callThisGuy(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "01994660589"));
        run(intent);
    }

    public void findThisGuy(View view){
        Uri location = Uri.parse("geo:23.87045238064482, 90.4191694120802?z=18");
        Intent intent = new Intent(Intent.ACTION_VIEW, location);
        run(intent);
    }

}