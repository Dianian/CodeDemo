package com.dainian.viewdemolibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dainian.viewdemolibrary.layoutview.Main2Activity;

/**
 * @author By FuBowen
 * @date 2018/6/11 10:36
 */
public class ViewFragment extends Fragment {

    private Button mBtnTitle;
    private Button mBtnCustomizeTextView;
    private Button mBtnCustomizeView;
    private TitleView mTitleview;
    private TextView1 mTv1;
    private TextView2 mTv2;
    private RoundView mRoundView;
    private Button mBtnEvent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_demo, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mBtnTitle = view.findViewById(R.id.btn_title);
        mBtnCustomizeTextView = view.findViewById(R.id.btn_customize_textView);
        mBtnCustomizeView = view.findViewById(R.id.btn_customize_view);
        mTitleview = view.findViewById(R.id.titleview);
        mTv1 = view.findViewById(R.id.tv1);
        mTv2 = view.findViewById(R.id.tv2);
        mRoundView = view.findViewById(R.id.round_view);
        mBtnEvent = view.findViewById(R.id.btn_event);
    }

    private void initEvent() {
        mBtnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleview.setVisibility(View.VISIBLE);
            }
        });
        mBtnCustomizeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTv1.setVisibility(View.VISIBLE);
                mTv2.setVisibility(View.VISIBLE);
            }
        });
        mBtnCustomizeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRoundView.setVisibility(View.VISIBLE);
            }
        });

        mBtnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Main2Activity.class));
            }
        });
    }
}
