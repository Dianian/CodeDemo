package com.dainian.httpsimplelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author By FuBowen
 * @date 2018/7/3 13:51
 */
public class HttpSimpleFragment extends Fragment {

    private Button mSendRequest;
    private Button mSendOkhttpRequest;
    private TextView mResponseText;
    private Button mBtnWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_http_simple, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mSendRequest = view.findViewById(R.id.send_request);
        mSendOkhttpRequest = view.findViewById(R.id.send_okhttp_request);
        mResponseText = view.findViewById(R.id.response_text);
        mBtnWebView = view.findViewById(R.id.btn_web_view);
    }

    private void initEvent() {
        mSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestHttp();
            }
        });
        mSendOkhttpRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestOkHttp();
            }
        });

        mBtnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),WebViewSinpleActivity.class));
            }
        });
    }

    private void sendRequestHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL("http://www.qq.com");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");//设置HTTP请求使用的方法
                    urlConnection.setConnectTimeout(8000);//设置连接超时
                    urlConnection.setReadTimeout(8000);//设置读取超时
                    InputStream inputStream = urlConnection.getInputStream();//获取服务器返回的输入流
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    showResponse(stringBuffer.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();//关闭HTTP连接
                    }
                }

            }
        }).start();
    }

    private void sendRequestOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    showResponse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void showResponse(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                mResponseText.setText(response);
            }
        });
    }

}
