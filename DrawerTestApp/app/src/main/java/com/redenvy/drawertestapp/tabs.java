package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.redenvy.drawertestapp.databinding.ActivityTabsBinding;

public class tabs extends AppCompatActivity {
    private ActivityTabsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // adding new tabs and setting names
        binding.tabBar.addTab(binding.tabBar.newTab().setText(R.string.tabs_title_frag1).setIcon(android.R.drawable.ic_dialog_dialer));
        binding.tabBar.addTab(binding.tabBar.newTab().setText(R.string.tabs_title_frag2).setIcon(android.R.drawable.ic_menu_call));
        binding.tabBar.addTab(binding.tabBar.newTab().setText(R.string.tabs_title_frag3).setIcon(android.R.drawable.ic_search_category_default));
        binding.tabBar.addTab(binding.tabBar.newTab().setText(R.string.tabs_title_frag4).setIcon(android.R.drawable.ic_dialog_email));
        binding.tabBar.addTab(binding.tabBar.newTab().setText(R.string.tabs_title_frag5).setIcon(android.R.drawable.ic_input_add));

        // automatically divide the tab bar space equally for each tab
        binding.tabBar.setTabGravity(binding.tabBar.GRAVITY_FILL);


    }
}