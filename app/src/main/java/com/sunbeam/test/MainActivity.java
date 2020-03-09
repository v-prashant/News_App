package com.sunbeam.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TabLayout TabLayout;
    private ViewPager viewPager;
    private MyPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        TabLayout = findViewById(R.id.tablayout);


        adapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout.setTabsFromPagerAdapter(adapter);


        // to keep working together of tab layout and view pager
        TabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(TabLayout));


        // for top bar of activity
        ActionBar bar = getSupportActionBar();
        bar.setTitle("News list");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("blue")));

    }



}
