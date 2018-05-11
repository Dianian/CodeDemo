package com.dainian.codedemo.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dainian.codedemo.R;

/**
 * @author 作者 FuBowen
 * @version 创建时间：2018/5/11 11:03
 */
public class PermissionsFragment extends Fragment {

    private Button mBtnCallPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissions, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mBtnCallPhone = view.findViewById(R.id.btn_call_phone);
    }

    private void initEvent(){
        mBtnCallPhone.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                call();
            }
        });
    }


    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            getActivity().startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * 申请权限后回调方法
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    call();
                break;
            default:
                Toast.makeText(getActivity(), "请授权", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
