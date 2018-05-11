package com.dainian.codedemo.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dainian.codedemo.R;
import com.dainian.codedemo.demoBroadcast.MyReceiver;

/**
 * @author 作者 FuBowen
 * @version 创建时间：2018/5/10 15:50
 */
public class BroadcastReceiverFragment extends Fragment {


    IntentFilter mIntentFilter;
    NetworkChangeReceiver networkChangeReceiver;
    private Button mBtnRegistration;
    private Button mBtnStaticRegistration;
    private Button mBtnLocal;
    private Button mBtnDemo;

    IntentFilter mLocalIntentFilter = new IntentFilter();
    MyReceiver mMyReceiver = new MyReceiver();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broadcast, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mBtnRegistration = view.findViewById(R.id.btn_registration);
        mBtnStaticRegistration = view.findViewById(R.id.btn_static_registration);
        mBtnLocal = view.findViewById(R.id.btn_local);
        mBtnDemo = view.findViewById(R.id.btn_demo);
    }

    private void initEvent() {
        //动态注册广播
        mBtnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntentFilter = new IntentFilter();
                mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                networkChangeReceiver = new NetworkChangeReceiver();
                getActivity().registerReceiver(networkChangeReceiver, mIntentFilter);
            }
        });
        //发送广播
        //广播接收器为静态注册
        mBtnStaticRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("000000");
                getActivity().sendBroadcast(intent);
            }
        });
        //本地广播注册
        mLocalIntentFilter.addAction("000000");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMyReceiver, mLocalIntentFilter);
        //本地广播发送
        mBtnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("000000");
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            }
        });

        //强制下线
        mBtnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("000");
                getActivity().sendBroadcast(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //动态广播取消注册
        if (networkChangeReceiver!=null){
            getActivity().unregisterReceiver(networkChangeReceiver);
        }
        //本地广播取消注册
        if (mMyReceiver!=null){
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMyReceiver);
        }
    }


    /**
     * 网络切换的广播接收器
     */
    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "net work", Toast.LENGTH_SHORT).show();
        }
    }
}
