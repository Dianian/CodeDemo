package com.dainian.notificationlibrary;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author By FuBowen
 * @date 2018/6/15 14:43
 */
public class NotificationFragment extends Fragment {

    private Button mSendNotice;
    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSendNotice = view.findViewById(R.id.send_notice);
        mSendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, mActivity.getClass());
                PendingIntent pendingIntent = PendingIntent.getActivity(mActivity, 0, intent, 0);
                NotificationManager manager = (NotificationManager) mActivity.getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(mActivity)
                        .setContentTitle("标题")
                        .setContentText("内容文字")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.penguin)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.penguin))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1, notification);
            }
        });
    }
}
