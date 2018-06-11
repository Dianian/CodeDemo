package com.dainian.viewdemolibrary.layoutview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private static final String TAG = "MyView";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "View onTouchEvent" + event.getAction());
        //事件处理方法
        //返回true不继续传递
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "View dispatchTouchEvent" + event.getAction());
        //事件传递方法
        //事件分发的第一步
        return super.dispatchTouchEvent(event);
    }
}
