package com.dainian.codedemo.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dainian.codedemo.R;
import com.dainian.codedemo.demoAdapter.TabLayoutAdapter;

public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout mTab;
    private ViewPager mViewpager;
    private TabLayoutAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initView();
    }


    private void initView() {
        mTab = findViewById(R.id.tab);
        mViewpager = findViewById(R.id.viewpager);
        mViewPagerAdapter = new TabLayoutAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mViewPagerAdapter);
        mTab.setupWithViewPager(mViewpager);
        mTab.getTabAt(0).setText(mViewPagerAdapter.getPageTitle(0));
        mTab.getTabAt(1).setText("Tab 2");
        mTab.getTabAt(2).setText("Tab 3").setIcon(R.mipmap.ic_launcher);
    }
}
