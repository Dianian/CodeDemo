package com.dainian.mediademolibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class VideoActivity extends AppCompatActivity {
    private Button mPlay;
    private Button mPause;
    private Button mReplay;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initEvent();

        if (ContextCompat.checkSelfPermission(VideoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VideoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideo();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideo();
                } else {
                    //请授权
                }
                break;
            default:
                break;
        }
    }

    private void initView() {
        mPlay = findViewById(R.id.play);
        mPause = findViewById(R.id.pause);
        mReplay = findViewById(R.id.replay);
        mVideoView = findViewById(R.id.video_view);
    }

    private void initVideo() {
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");//根目录文件
        mVideoView.setVideoPath(file.getPath());
    }

    private void initEvent() {
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mVideoView.isPlaying()) {
                    mVideoView.start();//开始
                }
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mVideoView.isPlaying()) {
                    mVideoView.pause();//暂停
                }
            }
        });

        mReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mVideoView.isPlaying()) {
                    mVideoView.resume();//重新播放
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.suspend();//释放资源
        }
    }
}
