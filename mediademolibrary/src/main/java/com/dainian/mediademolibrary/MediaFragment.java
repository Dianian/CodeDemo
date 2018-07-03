package com.dainian.mediademolibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author By FuBowen
 * @date 2018/7/3 14:25
 */
public class MediaFragment extends Fragment {

    private Button mBtnVideo;
    private Button mBtnAudio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mBtnVideo = view.findViewById(R.id.btn_video);
        mBtnAudio = view.findViewById(R.id.btn_audio);
    }

    private void initEvent() {
        mBtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });
        mBtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AudioActivity.class));
            }
        });
    }
}
