package com.dainian.viewdemolibrary.layoutview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewGroupA extends LinearLayout {
    private static final String TAG = "MyViewGroupA";

    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "ViewGroupA onTouchEvent" + event.getAction());
        //事件处理方法
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "ViewGroupA onInterceptTouchEvent" + ev.getAction());
        //事件拦截方法
        //返回true拦截
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "ViewGroupA dispatchTouchEvent" + ev.getAction());
        //事件传递方法
        //事件分发的第一步
        return super.dispatchTouchEvent(ev);
    }
}
