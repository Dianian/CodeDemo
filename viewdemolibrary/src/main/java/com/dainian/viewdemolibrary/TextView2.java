package com.dainian.viewdemolibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextView2 extends TextView {
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mTranslate = 0;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;

    public TextView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();//获取当前的 TextView 的 Panint
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.BLUE, 0xffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);//线性渐变
                mPaint.setShader(mLinearGradient);//设置渲染器
                mMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置矩阵的平移
        if (mMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(100);
        }
    }
}
