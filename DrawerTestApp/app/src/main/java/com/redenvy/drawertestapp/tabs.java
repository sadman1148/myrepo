package com.redenvy.drawertestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.redenvy.drawertestapp.databinding.ActivityTabsBinding;

public class tabs extends AppCompatActivity implements dataPasser {
    private ActivityTabsBinding binding;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // adding new tabs and setting names
        initTab(android.R.drawable.ic_dialog_dialer);
        initTab(android.R.drawable.ic_menu_call);
        initTab(android.R.drawable.ic_search_category_default);
        initTab(android.R.drawable.ic_dialog_email);
        initTab(android.R.drawable.ic_menu_add);

        // automatically divide the tab bar space equally for each tab
        binding.tabBar.setTabGravity(binding.tabBar.GRAVITY_FILL);

        final FragmentAdapter adapter = new FragmentAdapter(this,getSupportFragmentManager(),binding.tabBar.getTabCount());
        binding.viewpager.setAdapter(adapter);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabBar));
        binding.tabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                binding.viewpager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){}
            @Override
            public void onTabReselected(TabLayout.Tab tab){}
        });

        // secret messages
        String f1m = "Activity to Fragment 1, do you copy? Over.";
    }

    public void receiveData(Object obj,int fragmentNo) {
        switch (fragmentNo){
            case 1:
                binding.tabsText.setText("Fragment "+fragmentNo+" sent: "+(String)obj);
                break;
            case 2:
                binding.tabsText.setText("Fragment "+fragmentNo+" sent: "+(int)obj);
                break;
            case 3:
                binding.tabsText.setText("Fragment "+fragmentNo+" sent: "+(boolean)obj);
                break;
            case 4:
                binding.tabsText.setText("Fragment "+fragmentNo+" sent: "+(char)obj);
                break;
            case 5:
                binding.tabsText.setText("Fragment "+fragmentNo+" sent: "+(double)obj);
                break;
            default:
                return;
        }
    }

    public void initTab(int i){
        binding.tabBar.addTab(binding.tabBar.newTab().setIcon(i));
    }

}