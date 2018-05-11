package com.dainian.codedemo.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dainian.codedemo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author 作者 FuBowen
 * @version 创建时间：2018/5/11 11:27
 */
public class DataBaseFragment extends Fragment {


    private EditText mEtOutput;
    private Button mBtnOutput;
    private Button mBtnInput;
    private TextView mTvInput;
    private EditText mEtWriteSp;
    private Button mBtnWriteSp;
    private Button mBtnReadSp;
    private TextView mTvReadSp;
    private Button mBtnCreate;
    private Button mBtnAdd;
    private Button mBtnDelete;
    private Button mBtnUpdata;
    private Button mBtnQuery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_base, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mEtOutput = view.findViewById(R.id.et_output);
        mBtnOutput = view.findViewById(R.id.btn_output);
        mBtnInput = view.findViewById(R.id.btn_input);
        mTvInput = view.findViewById(R.id.tv_input);
        mEtWriteSp = view.findViewById(R.id.et_write_sp);
        mBtnWriteSp = view.findViewById(R.id.btn_write_sp);
        mBtnReadSp = view.findViewById(R.id.btn_read_sp);
        mTvReadSp = view.findViewById(R.id.tv_read_sp);
        mBtnCreate = view.findViewById(R.id.btn_create);
        mBtnAdd = view.findViewById(R.id.btn_add);
        mBtnDelete = view.findViewById(R.id.btn_delete);
        mBtnUpdata = view.findViewById(R.id.btn_updata);
        mBtnQuery = view.findViewById(R.id.btn_query);
    }

    private void initEvent() {
        //写入文件
        mBtnOutput.setOnClickListener(v -> {
            String data = mEtOutput.getText().toString();
            FileOutputStream fileOutputStream = null;
            BufferedWriter bufferedWriter = null;

            try {
                fileOutputStream = getActivity().openFileOutput("testData.txt", MODE_PRIVATE);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                bufferedWriter.write(data);
                Toast.makeText(getContext(), "写入完成！", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //读取文件
        mBtnInput.setOnClickListener(v -> {
            FileInputStream fileInputStream = null;
            BufferedReader bufferedReader = null;
            try {
                fileInputStream = getActivity().openFileInput("testData.txt");
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = "";
                StringBuffer sb = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.read();
                mTvInput.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //SP写入
        mBtnWriteSp.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("SPdata", MODE_PRIVATE).edit();
            editor.putString("str", mEtWriteSp.getText().toString());
            editor.apply();
            Toast.makeText(getContext(), "写入完成！", Toast.LENGTH_SHORT).show();
        });

        //SP读取
        mBtnReadSp.setOnClickListener(v -> {
            SharedPreferences preferences = getActivity().getSharedPreferences("SPdata", MODE_PRIVATE);
            String s = preferences.getString("str", "");
            mTvReadSp.setText(s);
        });
    }


}
