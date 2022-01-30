package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.redenvy.justdoit.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val themeSwitch  = findPreference<SwitchPreferenceCompat>(getString(R.string.theme_switch_key))

    }
}