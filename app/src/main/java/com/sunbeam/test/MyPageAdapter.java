package com.sunbeam.test;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class MyPageAdapter extends FragmentStatePagerAdapter {



    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
       // Log.e("pra"," "+i);
        fragment frag = new fragment(i);
        return frag;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return "NEWS "+(position+1);
    }
}
