package com.redenvy.drawertestapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public FragmentAdapter(Context c, FragmentManager fm, int tt){
        super(fm);
        context = c;
        totalTabs = tt;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                frag1 f1 = new frag1();
                return f1;
            case 1:
                frag2 f2 = new frag2();
                return f2;
            case 2:
                frag3 f3 = new frag3();
                return f3;
            case 3:
                frag4 f4 = new frag4();
                return f4;
            case 4:
                frag5 f5 = new frag5();
                return f5;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}