package com.dainian.codedemo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.dainian.codedemo.fragments.ActivityFragment;
import com.dainian.codedemo.fragments.BroadcastReceiverFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private DrawerLayout mDeaweerLayout;
    private NavigationView mNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addFragment(new ActivityFragment());
    }

    private void initView() {
        mToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);

        mDeaweerLayout = findViewById(R.id.DeaweerLayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        mNav = findViewById(R.id.nav);
        mNav.setCheckedItem(R.id.item_activity);//设置默认选中项
        //Nav 界面的监听
        mNav.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_activity:
                    addFragment(new ActivityFragment());
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_broadcast:
                    addFragment(new BroadcastReceiverFragment());
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.itrm_service:
                    addFragment(new ActivityFragment());
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.itrm_database:
                    addFragment(new ActivityFragment());
                    mDeaweerLayout.closeDrawers();
                    break;
            }
            return true;
        });

    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDeaweerLayout.openDrawer(Gravity.START);
                break;
        }
        return true;
    }
}
