package com.dainian.codedemo.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
    private Button mBtnAlertdialog;
    private Button mBtnProgressdialog;

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
        mBtnAlertdialog = view.findViewById(R.id.btn_alertdialog);
        mBtnProgressdialog = view.findViewById(R.id.btn_progressdialog);
    }

    private void initEent() {
        mBtnTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TabLayoutActivity.class));
            }
        });

        mBtnAlertdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("标题");
                dialog.setMessage("信息");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
            }
        });

        mBtnProgressdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog
                        (getActivity());
                progressDialog.setTitle("标题");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);//是否取消
                progressDialog.show();
            }
        });
    }


}
