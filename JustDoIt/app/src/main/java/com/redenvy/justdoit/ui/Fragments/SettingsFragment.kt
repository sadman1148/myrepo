package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.redenvy.justdoit.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}