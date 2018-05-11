package com.dainian.codedemo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dainian.codedemo.R;
import com.dainian.codedemo.activitys.TabLayoutActivity;

/**
 * @author 作者 FuBowen
 * @version 创建时间：2018/5/10 15:48
 */
public class ActivityFragment extends Fragment {


    private Button mBtnTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        initView(view);
        initEent();
        return view;
    }

    private void initView(View view) {
        mBtnTabLayout = view.findViewById(R.id.btn_tab_layout);
    }

    private void initEent() {
        mBtnTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TabLayoutActivity.class));
            }
        });
    }
}
