package com.dainian.threaddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author By FuBowen
 * @date 2018/6/21 15:54
 */
public class ThreadsFragment extends Fragment {


    private static final int MSG_1 = 1;
    private Button mBtnHandler;
    private TextView mTvHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thread, container, false);
        initView(view);
        initEvent();
        return view;
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_1:
                    mTvHandler.setText("更改………………");
                    break;

            }
        }
    };


    private void initEvent() {
        mBtnHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
//                       mText.setText("更改………………");
//                   }
//               });

                new Thread(new Runnable() {//开启线程
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = MSG_1;
                        mHandler.sendMessage(message);
                        //1.创建 Handler 对象
                        //2.由Handle 发送 Message 消息到 MessageQueue 等待处理
                        //3.Looper 一直尝试从 MessageQueue中取出消息分发回 Handler 的 handleMessahe() 方法
                    }
                }).start();

            }
        });
    }

    private void initView(View view) {
        mBtnHandler = view.findViewById(R.id.btn_handler);
        mTvHandler = view.findViewById(R.id.tv_handler);
    }
}
