package com.dainian.codedemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dainian.codedemo.fragments.ActivityFragment;
import com.dainian.codedemo.fragments.BroadcastReceiverFragment;
import com.dainian.codedemo.fragments.DataBaseFragment;
import com.dainian.codedemo.fragments.PermissionsFragment;
import com.dainian.httpsimplelibrary.HttpSimpleFragment;
import com.dainian.mediademolibrary.MediaFragment;
import com.dainian.notificationlibrary.NotificationFragment;
import com.dainian.photolibrary.PhotoFragment;
import com.dainian.providercontactslibrary.ProviderContactsFragment;
import com.dainian.rxjava2demolibrary.RxJava2Fragment;
import com.dainian.threaddemo.ThreadsFragment;
import com.dainian.viewdemolibrary.ViewFragment;
import com.example.servicedemolibrary.ServiceDemoFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private DrawerLayout mDeaweerLayout;
    private NavigationView mNav;

    //强制下线Demo
    IntentFilter mIntentFilter;
    outReceiver mOutReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
        setContentView(R.layout.activity_main);
        initView();
        addFragment(new ActivityFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //强制下线相关代码
        mIntentFilter = new IntentFilter("000");
        mOutReceiver = new outReceiver();
        registerReceiver(mOutReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mOutReceiver != null) {
            unregisterReceiver(mOutReceiver);
            mOutReceiver = null;
        }
    }

    private void initView() {
        mToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);

        mDeaweerLayout = findViewById(R.id.DeaweerLayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.penguin);
        }

        mNav = findViewById(R.id.nav);
        mNav.setCheckedItem(R.id.item_activity);//设置默认选中项
        //Nav 头部的监听
        mNav.getHeaderView(0).setOnClickListener(v -> {
            Toast.makeText(this, "TTT", Toast.LENGTH_SHORT).show();
        });
        //Nav 界面的监听
        mNav.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_activity:
                    addFragment(new ActivityFragment());
                    mToolBar.setTitle("Activity");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_broadcast:
                    addFragment(new BroadcastReceiverFragment());
                    mToolBar.setTitle("BroadcastReceiver");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_service:
                    addFragment(new ServiceDemoFragment());
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_provider:
                    addFragment(new ProviderContactsFragment());
                    mToolBar.setTitle("Provider 联系人示例");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_database:
                    addFragment(new DataBaseFragment());
                    mToolBar.setTitle("存储相关");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_notification:
                    addFragment(new NotificationFragment());
                    mToolBar.setTitle("通知相关");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_hreads:
                    addFragment(new ThreadsFragment());
                    mToolBar.setTitle("线程相关");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_photo:
                    addFragment(new PhotoFragment());
                    mToolBar.setTitle("相册相机相关");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_http:
                    addFragment(new HttpSimpleFragment());
                    mToolBar.setTitle("Http/WebView简单使用");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_permissions:
                    addFragment(new PermissionsFragment());
                    mToolBar.setTitle("权限申请");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_view:
                    addFragment(new ViewFragment());
                    mToolBar.setTitle("View/自定义View");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_media:
                    addFragment(new MediaFragment());
                    mToolBar.setTitle("简单播放本地音视频");
                    mDeaweerLayout.closeDrawers();
                    break;
                case R.id.item_rxjava2:
                    addFragment(new RxJava2Fragment());
                    mToolBar.setTitle("Rxjava2Demo");
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

    class outReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("提示");
            builder.setMessage("强制下线");
            builder.setCancelable(false);
            builder.setPositiveButton("确定", (dialog, which) -> {
                finish();
//                    ActivityCollecter.finishAll();
//                    context.startActivity(new Intent(context, LoginActivity.class));
            });
            builder.show();
        }
    }
}
