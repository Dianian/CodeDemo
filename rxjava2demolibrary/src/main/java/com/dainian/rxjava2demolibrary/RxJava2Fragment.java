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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

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
    private Button mBtnFromIterable;
    private Button mBtnDefer;
    private Button mBtnTimer;
    private Button mBtnInterval;
    private Button mBtnIntervalRange;
    private Button mBtnRange;
    private Button mBtnRangeLong;
    private Button mBtnEoe;
    private Button mBtnMap;
    private Button mBtnFlatMap;
    private Button mBtnConcatMap;
    private Button mBtnBuffer;
    private Button mBtnGroupBy;
    private Button mBtnScan;
    private Button mBtnWindow;

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
        mBtnFromIterable = view.findViewById(R.id.btn_from_iterable);
        mBtnDefer = view.findViewById(R.id.btn_defer);
        mBtnTimer = view.findViewById(R.id.btn_timer);
        mBtnInterval = view.findViewById(R.id.btn_interval);
        mBtnIntervalRange = view.findViewById(R.id.btn_interval_range);
        mBtnRange = view.findViewById(R.id.btn_range);
        mBtnRangeLong = view.findViewById(R.id.btn_range_long);
        mBtnEoe = view.findViewById(R.id.btn_eoe);
        mBtnMap = view.findViewById(R.id.btn_map);
        mBtnFlatMap = view.findViewById(R.id.btn_flat_map);
        mBtnConcatMap = view.findViewById(R.id.btn_concat_map);
        mBtnBuffer = view.findViewById(R.id.btn_buffer);
        mBtnGroupBy = view.findViewById(R.id.btn_groupBy);
        mBtnScan = view.findViewById(R.id.btn_scan);
        mBtnWindow = view.findViewById(R.id.btn_window);
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

        RxView.clicks(mBtnFromIterable).subscribe(o -> fromIterable());//fromIterable

        RxView.clicks(mBtnDefer).subscribe(o -> defer());//defer

        RxView.clicks(mBtnTimer).subscribe(o -> timer());//timer

        RxView.clicks(mBtnInterval).subscribe(o -> interval());//interval

        RxView.clicks(mBtnIntervalRange).subscribe(o -> intervalRange());//intervalRange

        RxView.clicks(mBtnRange).subscribe(o -> range());//range

        RxView.clicks(mBtnRangeLong).subscribe(o -> rangeLong());//rangLong

        RxView.clicks(mBtnEoe).subscribe(o -> ene());//empty() & never() & error()

//        =================================转换操作符======================================
        RxView.clicks(mBtnMap).subscribe(o -> map()); //map()

        RxView.clicks(mBtnFlatMap).subscribe(o -> flatMap());  //flatMap()

        RxView.clicks(mBtnConcatMap).subscribe(o -> concatMap());//concatMap

        RxView.clicks(mBtnBuffer).subscribe(o -> bufferr());//bufferr

        RxView.clicks(mBtnGroupBy).subscribe(o -> groupBy());//groupBy

        RxView.clicks(mBtnScan).subscribe(o -> scan());//Scan
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

    /**
     * 创建被观察者，并发送事件，不超过10个
     */
    private void just() {
        //just 操作度
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

    /**
     * 和 just() 类似，可以传入多于十个的变量，可以传入一个数组
     */
    private void fromArray() {
        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13})
                .subscribe(new Observer<Integer>() {
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

    /**
     * Callable 会返回一个结果值，这个结果值会发送给观察者
     */
    private void fromCallable() {
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

    /**
     * fromFuture 增加了方法操作 Callable
     */
    private void fromFuture() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "结果";
            }
        });
        Observable.fromFuture(futureTask)
                .doOnSubscribe(new Consumer<Disposable>() {//只有在订阅时才会发送事件
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

    /**
     * 发送一个集合给观察者
     */
    private void fromIterable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);
        Observable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "accept: " + integer);
            }
        });
    }

    /**
     * defer方法就是 直到观察者被订阅才会创建被观察者
     */
    private void defer() {
//        final Integer finalI = 10;
//        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.just(finalI);
//            }
//        });
////        i = 99;
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        observable.subscribe(observer);
//        finalI = 11111;
//        observable.subscribe(observer);
    }

    /**
     * 当到了一定的时间后，发送一个事件给观察者
     */
    private void timer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "accept:发送成功！ ");
            }
        });
    }

    /**
     * 每间隔一定时间 发送事件给观察者
     */
    private void interval() {
        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "accept:发送成功！ " + aLong);

            }
        });
    }

    /**
     * 和 interval() 一样但是可以指定 起始值，数量，起始延时
     */
    private void intervalRange() {
        Observable.intervalRange(2, 5, 10, 3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "accept:发送成功！ " + aLong);
            }
        });
    }

    /**
     * 可以发送一定范围的事件序列,起始值，发送数量
     */
    private void range() {
        Observable.range(2, 5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "accept: " + integer);
            }
        });
    }

    /**
     * 和range一样 ，数据类型为long
     */
    private void rangeLong() {
        Observable.rangeLong(2, 5).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "accept: " + aLong);
            }
        });
    }

    /**
     * empty()：直接发送 onComplete() 事件
     * never()：不发送任何事件
     * error()：发送 onError() 事件
     */
    private void ene() {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };
        //直接发送Complete事件
        Observable.empty().subscribe(observer);
        //不发送任何事件
        Observable.never().subscribe(observer);
        //发送Error事件
        Observable.error(new Throwable("错误")).subscribe(observer);
    }

    /**
     * 将 被观察者 发送的数据转换为其他类型
     */
    private void map() {
        Observable.just(1, 21, 33).map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return "map" + integer;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG, "accept: " + o);
            }
        });
    }

    /**
     * 与map相似 返回一个新的被观察者 ,输出结果 无序
     */
    private void flatMap() {
        List<Person> personList = getPerson();
        Observable.fromIterable(personList).flatMap(new Function<Person, ObservableSource<Plan>>() {
            @Override
            public ObservableSource<Plan> apply(Person person) throws Exception {
                return Observable.fromIterable(person.getPlanList());
            }
        }).flatMap(new Function<Plan, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Plan plan) throws Exception {
                return Observable.fromIterable(plan.getActionList());
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: " + s);
            }
        });
    }

    /**
     * 与 flatMap 一样 输出结果有序
     */
    private void concatMap() {
        List<Person> personList = getPerson();
        Observable.fromIterable(personList).concatMap(new Function<Person, ObservableSource<Plan>>() {
            @Override
            public ObservableSource<Plan> apply(Person person) throws Exception {
                return Observable.fromIterable(person.getPlanList());
            }
        }).concatMap(new Function<Plan, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Plan plan) throws Exception {
                return Observable.fromIterable(plan.getActionList());
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: " + s);
            }
        });
    }


    private List<Person> getPerson() {
        List<Person> mPersonList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setName("名字" + i);
            List<Plan> plans = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Plan plan = new Plan();
                plan.setTime("时间" + j);
                plan.setContent("内容" + i + j);
                List<String> strings = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    String s = "字符" + i + j + k;
                    strings.add(s);
                    plan.setActionList(strings);
                }
                plans.add(plan);
                person.setPlanList(plans);
            }
            mPersonList.add(person);
        }
        return mPersonList;
    }

    /**
     * 当缓冲区存够一定的数量后一并发出
     */
    private void bufferr() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(2, 1)// 数量  /输出后跳过几个元素
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.e(TAG, "缓冲区大小" + integers.size());

                        for (Integer i : integers) {
                            Log.e(TAG, "accept: " + i);
                        }
                    }
                });
    }

    /**
     * 对发送的数据进行分组 ，每组都会返回一个被观察者
     */
    private void groupBy() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy(new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) throws Exception {
                        Log.e(TAG, String.valueOf(integer));
                        Log.e(TAG, String.valueOf(integer % 3));
                        return integer % 3;
                    }
                }).subscribe(new Consumer<GroupedObservable<Object, Integer>>() {
            @Override
            public void accept(GroupedObservable<Object, Integer> objectIntegerGroupedObservable) throws Exception {
                objectIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "name" + objectIntegerGroupedObservable.getKey() + "value " + integer);
                    }
                });
            }
        });
    }

    /**
     *
     */
    private void scan() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return null;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });
    }
}
