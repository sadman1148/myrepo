package com.redenvy.permissionplease;

import static com.redenvy.permissionplease.R.string;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.redenvy.permissionplease.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binder;
    private File myExternalFile;
    private String myData;
    private String filename = Constant.EXTERNAL_FILE_NAME;
    private String filepath = Constant.EXTERNAL_FILE_DIRECTORY;
    private static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        checkPermissions();
        myExternalFile = new File(getExternalFilesDir(filepath), filename);
        binder.readButton.setOnClickListener(this);
        binder.writeButton.setOnClickListener(this);
        binder.editButton.setOnClickListener(this);
        binder.cleanButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.readButton:
                doRead();
                break;
            case R.id.writeButton:
                if (binder.input.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, string.nothingToSave, Toast.LENGTH_SHORT).show();
                } else {
                    doWrite();
                }
                View v = this.getCurrentFocus();
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                break;
            case R.id.editButton:
                binder.input.setText(myData);
                break;
            case R.id.cleanButton:
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(myExternalFile);
                    fileOutputStream.write("".getBytes());
                    myData = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                binder.input.setText("");
                binder.output.setText("");
                Toast.makeText(MainActivity.this, string.cleared, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void doRead() {
        try {
            FileInputStream fileInputStream = new FileInputStream(myExternalFile);
            DataInputStream inputStream = new DataInputStream(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                if(myData == null){
                    myData = text;
                }else{
                    myData = myData + "\n" + text;
                }
            }
            inputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        binder.output.setText(myData);
        Toast.makeText(MainActivity.this, string.loaded, Toast.LENGTH_SHORT).show();
    }

    public void doWrite() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myExternalFile);
            fileOutputStream.write(binder.input.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        binder.input.setText("");
        Toast.makeText(MainActivity.this, string.saved, Toast.LENGTH_SHORT).show();
    }

    public void checkPermissions() {
        int permissionRead = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionRead != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, 1);
        }
    }
}