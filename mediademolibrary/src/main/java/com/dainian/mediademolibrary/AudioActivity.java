package com.dainian.mediademolibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class AudioActivity extends AppCompatActivity {
    private Button mPlay;
    private Button mPause;
    private Button mStop;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        if (ContextCompat.checkSelfPermission(AudioActivity.this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AudioActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPlay();
        }
        initView();
        initEvent();

    }

    private void initMediaPlay() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");//SD卡根目录文件
            mMediaPlayer.setDataSource(file.getPath());//指定文件的路径
            mMediaPlayer.prepare();//进入到准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlay();
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void initView() {
        mPlay = findViewById(R.id.play);
        mPause = findViewById(R.id.pause);
        mStop = findViewById(R.id.stop);
    }

    private void initEvent() {
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (mMediaPlayer != null) {
//                    mMediaPlayer.start();//开始
//                }
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.pause();//暂停
                }
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.reset();//停止 将MediaPlayer对象重置到刚刚创建的状态
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();//停止
            mMediaPlayer.release();//释放掉与MediaPlayer对象相关的资源
        }
    }
}
