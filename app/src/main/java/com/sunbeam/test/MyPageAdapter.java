package com.sunbeam.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class MyPageAdapter extends FragmentStatePagerAdapter {


//http://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8

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

        if(position == 0)
            return "VICE-News";
        else if(position == 1)
            return "Ary News";  //ary-news
        else if(position ==2)
            return "BBC News";  //bbc-news
        else if(position ==3)
            return "BBC Sport"; //bbc-sport
        else if(position == 4)
            return "USA Today";    //cnbc
        else if(position ==5)
            return "CNN";    //cnn
        else if(position ==6)
            return "Fox News";  //fox-news
        else if(position ==7)
            return "Google News"; //google-news
        else if(position ==8)
            return "The Verge";
        else if(position ==9)
            return "News24";    //news24
        else
            return "hello";


    }
}
