package com.dainian.viewdemolibrary.layoutview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewGroupB extends LinearLayout {
    private static final String TAG = "MyViewGroupB";

    public MyViewGroupB(Context context) {
        super(context);
    }

    public MyViewGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "ViewGroupB onTouchEvent" + event.getAction());
        //事件处理方法
        //返回true不继续传递
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "ViewGroupB onInterceptTouchEvent" + ev.getAction());
        //事件拦截方法
        //返回true拦截
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "ViewGroupB dispatchTouchEvent" + ev.getAction());
        //事件传递方法
        //事件分发的第一步
        return super.dispatchTouchEvent(ev);
    }
}
