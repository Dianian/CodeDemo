package com.dainian.viewdemolibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextView1 extends TextView {
    private Paint mPaint1;
    private Paint mPaint2;

    public TextView1(Context context) {
        super(context);
        initPaint();
    }

    public TextView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public TextView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        //初始化 paint 对象
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //用canvas对象绘制矩形
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
        canvas.save();
        //  平移10像素
        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
