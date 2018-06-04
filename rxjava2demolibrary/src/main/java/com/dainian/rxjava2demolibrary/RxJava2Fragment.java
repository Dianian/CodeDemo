package com.dainian.rxjava2demolibrary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author By FuBowen
 * @date 2018/6/4 13:41
 */
public class RxJava2Fragment extends Fragment {
    private static final String TAG = "RxJava2Fragment";
    private Button mBtnAbc;
    private Button mBtnJust;
    private Button mBtnFromArray;
    private Button mBtnFromCallable;
    private Button mBtnFromFutuer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxjava, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mBtnAbc = view.findViewById(R.id.btn_abc);
        mBtnJust = view.findViewById(R.id.btn_just);
        mBtnFromArray = view.findViewById(R.id.btn_from_array);
        mBtnFromCallable = view.findViewById(R.id.btn_from_callable);
        mBtnFromFutuer = view.findViewById(R.id.btn_from_futuer);
    }

    private void initEvent() {
        RxView.clicks(mBtnAbc).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                //Rxjava2 基本使用
                abc();
            }
        });
        RxView.clicks(mBtnJust).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                //just 操作符的使用
                just();
            }
        });
        RxView.clicks(mBtnFromArray).subscribe(o -> fromArray());//romArray 操作符的使用

        RxView.clicks(mBtnFromCallable).subscribe(o -> fromCallable());//fromCallable 操作符的使用

        RxView.clicks(mBtnFromFutuer).subscribe(o -> fromFuture());//fromFutrue 操作符的使用
    }

    private void abc() {
        //创建 被观察者
        //操作符 create 创建一个 被观察者 Obervable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(1);
                emitter.onNext(1);
                emitter.onComplete();
            }
        });

        //创建观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                //开始时调用
                Log.e(TAG, "onSubscribe:");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                // Error 事件发送后其他事件 不会继续调用
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                //完成时调用
                //Complete 时间发送后，其他时间不会继续发送
                Log.e(TAG, "onComplete: ");
            }
        };

        //订阅
        observable.subscribe(observer);
    }

    private void just() {
        //just 操作度
        //创建被观察者，并发送事件，不超过10个
        Observable.just(1, 2, 3).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        });
    }

    private void fromArray() {
        //FromArray 操作符
        //和 just() 类似，可以传入多于十个的变量，可以传入一个数组
        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13}).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        });
    }

    private void fromCallable() {
        // Callable 会返回一个结果值，这个结果值会发送给观察者
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void fromFuture() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "结果";
            }
        });
        Observable.fromFuture(futureTask)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
//                        Log.e(TAG, "accept: " + futureTask.get());
                        futureTask.run();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "accept: get" + futureTask.get());
                        Log.e(TAG, "accept: " + s);
                    }
                });

    }
}
