package com.dainian.codedemo.demoFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dainian.codedemo.R;

/**
 * @author 作者 FuBowen
 * @version 创建时间：2018/5/11 9:03
 */
public class TabLayoutDemoFragment extends Fragment {
    public static TabLayoutDemoFragment newInstance() {

        Bundle args = new Bundle();

        TabLayoutDemoFragment fragment = new TabLayoutDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false);
    }
}
