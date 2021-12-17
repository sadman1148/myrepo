package com.redenvy.asyncapp;

import android.accessibilityservice.AccessibilityService;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText val1,val2;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val1 = (EditText) findViewById(R.id.val1);
        val2 = (EditText) findViewById(R.id.val2);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String value1 = val1.getText().toString();
                String value2 = val2.getText().toString();
                runner.execute(value1,value2);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Calculating..."); // Calling onProgressUpdate()
            int prod = 0;
            try {
                prod = Integer.parseInt(params[0])* Integer.parseInt(params[1]);
                Thread.sleep(3000);
                resp = "Still calculating";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            resp = ""+prod;
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait until calculation ends...");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);
        }
    }
}