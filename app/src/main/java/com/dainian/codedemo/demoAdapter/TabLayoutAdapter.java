package com.dainian.codedemo.demoAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dainian.codedemo.demoFragment.TabLayoutDemoFragment;

/**
 * @author 作者 FuBowen
 * @version 创建时间：2018/5/11 9:02
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {

    private String[] mStrings = {"1", "2", "3"};

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TabLayoutDemoFragment.newInstance();
    }

    @Override
    public int getCount() {
        return mStrings.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }
}
