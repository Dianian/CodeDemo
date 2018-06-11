package com.dainian.viewdemolibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleView extends RelativeLayout {

    private Button mBtnLeft;
    private TextView mTvTitle;
    private Button mBtnRight;

    // 左按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    // 右按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    // 标题的属性值，即我们在atts.xml文件中定义的属性
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_view, this);//载入布局
        initView();
        //将 arrt 中定义的 declare——styleable 的所有属性储存到 TypedArray 中
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);

        //从 TypedArray 中取出对应的值，为要设置的属性赋值
        mTitle = typedArray.getString(R.styleable.TitleView_titleText);
        mTitleTextSize = typedArray.getDimension(R.styleable.TitleView_titleTextSize, 12);
        mTitleTextColor = typedArray.getColor(R.styleable.TitleView_titleTextColor, 0x000000);

        mLeftText = typedArray.getString(R.styleable.TitleView_leftText);
        mLeftTextColor = typedArray.getColor(R.styleable.TitleView_leftTextColor, 0x000000);
        mLeftBackground = typedArray.getDrawable(R.styleable.TitleView_leftTextBackground);

        mRightText = typedArray.getString(R.styleable.TitleView_rightText);
        mRightTextColor = typedArray.getColor(R.styleable.TitleView_rightTextColor, 0x000000);
        mRightBackground = typedArray.getDrawable(R.styleable.TitleView_rightTextBackground);

        typedArray.recycle();// 调用 TypedArray 的 recycle 方法 完成资源的回收

        //为控件赋值
        mTvTitle.setText(mTitle);
        mTvTitle.setTextSize(mTitleTextSize);
        mTvTitle.setTextColor(mTitleTextColor);

        mBtnLeft.setText(mLeftText);
        mBtnLeft.setTextColor(mLeftTextColor);
        mBtnLeft.setBackground(mLeftBackground);

        mBtnRight.setText(mRightText);
        mBtnRight.setTextColor(mRightTextColor);
        mBtnRight.setBackground(mRightBackground);

        //回调设置点击事件
    }

    private void initView() {
        mBtnLeft = findViewById(R.id.btn_left);
        mTvTitle = findViewById(R.id.tv_title);
        mBtnRight = findViewById(R.id.btn_right);
    }
}
