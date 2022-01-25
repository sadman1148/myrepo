package com.redenvy.justdoit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.redenvy.justdoit.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
//        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
//        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", false)
//
//        if(isNightModeOn){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            switch_btn.text = "Disable Dark Mode"
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            switch_btn.text = "Enable Dark Mode"
//        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
//            val manualSync : Preference? = findPreference<Preference>(getString(R.string.syncNow_key))
//            manualSync.setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener(){
//                onPreferenceTreeClick(preference:Preference){
//
//                }
//            })

        }
    }
}