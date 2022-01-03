package com.example.settingstrainingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "r3denvy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i(TAG, "Signature value: " + sharedPreferences.getString("signature", "NA"));
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            EditTextPreference editTextPreference = findPreference("signature");
            Log.i(TAG, "Signature value: " + editTextPreference.getText());

            ListPreference listPreference = findPreference("reply");
            Log.i(TAG, "Reply value: " + listPreference.getValue());

            SeekBarPreference seekBarPreference = findPreference("bright");
            Log.i(TAG, "Brightness value: " + seekBarPreference.getValue());

            CheckBoxPreference checkBoxPreference = findPreference("auto");
            Log.i(TAG, "Automatic brightness value: " + checkBoxPreference.isChecked());

            SwitchPreferenceCompat switchPreference = findPreference("sync");
            Log.i(TAG, "Sync value: " + switchPreference.isChecked());

            SwitchPreferenceCompat switchPreferenceDependent = findPreference("attachment");
            Log.i(TAG, "Attachment value: " + switchPreferenceDependent.isChecked());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}