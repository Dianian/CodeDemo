package com.example.servicedemolibrary;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ServiceDemoFragment extends Fragment {

    public static Activity sActivity;

    private Button mBtnStart;
    private Button mBtnStop;
    //
    private Button mBtnStartBind;
    private Button mBtnStopBind;
    private MyBindService.DownBinder mDownBinder;
    private Button mBtnStartIntent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_demo, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mBtnStart = view.findViewById(R.id.btn_start);
        mBtnStop = view.findViewById(R.id.btn_stop);
        mBtnStartBind = view.findViewById(R.id.btn_start_bind);
        mBtnStopBind = view.findViewById(R.id.btn_stop_bind);
        mBtnStartIntent = view.findViewById(R.id.btn_start_intent);
    }

    private void initEvent() {
        //启动和暂停服务
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), MyService.class);
                getActivity().startService(startIntent);
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(getActivity(), MyService.class);
                getActivity().stopService(stopIntent);
            }
        });

        //bindService 启动和暂停
        mBtnStartBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startBindService = new Intent(getActivity(), MyBindService.class);
                getActivity().bindService(startBindService, mServiceConnection, Context.BIND_AUTO_CREATE);//绑定服务
            }
        });
        mBtnStopBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().unbindService(mServiceConnection);//解绑服务
            }
        });

        //启动 IntentService
        mBtnStartIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyIntentService.class);
                getActivity().startService(intent);
            }
        });
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定成功时调用
            mDownBinder = (MyBindService.DownBinder) service;
            mDownBinder.startDown();
            mDownBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //解除绑定时调用

        }
    };
}
