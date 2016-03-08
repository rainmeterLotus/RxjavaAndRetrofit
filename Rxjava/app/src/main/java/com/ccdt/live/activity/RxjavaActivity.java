package com.ccdt.live.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import com.ccdt.live.R;
import com.ccdt.live.base.BaseActivity;
import com.ccdt.live.bean.Course;
import com.ccdt.live.bean.Student;
import com.ccdt.live.rxjava.RxRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 该类主要讲解rxjava的用法
 */
public class RxjavaActivity extends BaseActivity {

    //butterknife知识点
    @Bind(R.id.button_map_common)
    Button button_map_common;

    @Bind(R.id.button_scheduler)
    Button button_scheduler;

    @Bind(R.id.button_form)
    Button button_form;

    @Bind(R.id.button_flatmap)
    Button button_flatmap;

    @Bind(R.id.button_repeat)
    Button button_repeat;

    @Bind(R.id.button_timer)
    Button button_timer;

    @Bind(R.id.button_concat)
    Button button_concat;

    @Bind(R.id.button_merge)
    Button button_merge;

    @Bind(R.id.text_id)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button_map_common.setOnClickListener(c -> mapAndcommonButton());//lambda知识点
        button_scheduler.setOnClickListener(c -> schedulserButton());
        button_form.setOnClickListener(c -> fromButton());
        button_flatmap.setOnClickListener(c -> flatMapButton());
        button_repeat.setOnClickListener(c -> repeatButton());
        button_timer.setOnClickListener(c -> timerButton());
        button_concat.setOnClickListener(c -> concatButton());
        button_merge.setOnClickListener(c -> mergeButton());

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_rxjava;
    }



    /**
     * 合拼2个 observable（可以是2个网络请求）
     * 例子中的2个observable处理不同的网络请求，但是谁先执行看内部处理的数据所消耗的时间
     * 使用到的操作符：OperatorMerge
     * 使用到的OnSubscrible:OnSubscribeFromIterable
     */
    private void mergeButton() {


        RxRequest.getInstance(context).liveOprate(1,this,new HashMap<>());

        Observable<String> memoryObser = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("memoryObser");

            }
        }).subscribeOn(Schedulers.newThread());

        Observable<String> diskObser = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("diskObser");

            }
        }).subscribeOn(Schedulers.newThread());

        Observable.merge(memoryObser,diskObser).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("xx", "==mergeButton=======onCompleted==");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xx", "==mergeButton=======onError==" + e.getMessage());
                    }


                    @Override
                    public void onNext(String str) {
                        Log.d("xx", "==mergeButton=======onNext==" + str);
                    }
                });
    }

    private void concatButton() {

        Observable<String> memoryObser = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                if(true){
                    subscriber.onNext("memoryObser");
                }else{
                    subscriber.onCompleted();
                }
            }
        });

        Observable<String> diskObser = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if(true){
                    subscriber.onNext("diskObser");
                }else{
                    subscriber.onCompleted();
                }
            }
        });

        Observable<String> network = Observable.just("network");

        Observable.concat(memoryObser,diskObser,network)
                .first()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("xx", "==concatButton=======onCompleted==");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xx", "==concatButton=======onError==" + e.getMessage());
                    }


                    @Override
                    public void onNext(String str) {
                        Log.d("xx", "==concatButton=======onNext==" + str);
                    }
                });
    }

    /**
     * 定时器:几秒钟后执行网络请求
     *
     * 使用到的操作符：无
     * 使用到的OnSubscrible:OnSubscribeTimerOnce
     */
    private void timerButton() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Log.d("xx", "==timerButton=======onCompleted==");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("xx", "==timerButton=======onError==" + e.getMessage());
            }


            @Override
            public void onNext(Long aLong) {
                Log.d("xx", "==timerButton=======onNext==" + aLong);
            }
        });


    }

    private void repeatButton() {
        Observable.range(2, 3).repeat(3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("xx", "==repeatButton=======onCompleted==");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("xx", "==repeatButton=======onError==" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("xx", "==repeatButton=======onNext==" + integer);
            }
        });
    }

    /**
     * rxjava的flatMap功能
     * 举个例子：打印出10位学生(数组Student[])中每位学生所学的所有课程（数组/集合List<Course>）
     * 使用到的操作符：OperatorMerge、OperatorMap
     * 使用到的OnSubscrible:OnSubscribeFromIterable
     */
    private void flatMapButton() {
        List<Course> list = new ArrayList<Course>();
        list.add(new Course("Chinese"));
        list.add(new Course("Math"));
        list.add(new Course("History"));
        Student student1 = new Student("rain", list);
        list = new ArrayList<Course>();
        list.add(new Course("Chinese-1"));
        list.add(new Course("Math-2"));
        list.add(new Course("History-3"));
        Student student2 = new Student("meter", list);
        Student[] students = new Student[]{student1,student2};

        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourses());
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                Log.d("xx", "==flatMap=======onNext==" + course.getName());
            }
        });

    }

    /**
     * rxjava的from功能
     * 获取每位学生的姓名
     * 使用到的操作符：OperatorMap
     * 使用到的OnSubscrible:OnSubscribeFromIterable
     */
    private void fromButton() {

        List<Course> list = new ArrayList<Course>();
        list.add(new Course("Chinese"));
        list.add(new Course("Math"));
        list.add(new Course("History"));
        Student student1 = new Student("rain", list);
        list = new ArrayList<Course>();
        list.add(new Course("Chinese-1"));
        list.add(new Course("Math-2"));
        list.add(new Course("History-3"));
        Student student2 = new Student("meter", list);
        Student[] students = new Student[]{student1,student2};
        Subscriber<String> subscriber =  new Subscriber<String>(){
            @Override
            public void onCompleted() {
                Log.d("xx", "==from=======onCompleted==");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("xx", "==from=======onNext==" + s);
            }
        };

        Observable.from(students).map(new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                return student.getName();
            }
        }).observeOn(Schedulers.newThread()).subscribe(subscriber);

    }

    /**
     * rxjava的线程问题

     •Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
     •Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
     •Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，
     区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
     不要把计算工作放在 io() 中，可以避免创建不必要的线程。
     •Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，
     例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，
     否则 I/O 操作的等待时间会浪费 CPU。
     •另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。

     */
    private void schedulserButton() {

        Observable.create(new Observable.OnSubscribe<Map<String, String>>() {
            @Override
            public void call(Subscriber<? super Map<String, String>> subscriber) {
                HashMap<String, String> map = new HashMap<>();
                map.put("1", "1111");
                map.put("2", "2222");
                String name = Thread.currentThread().getName();
                Log.d("xx", "==map====call===currentThread==" + name);//RxCachedThreadScheduler-1
                subscriber.onNext(map);
                subscriber.unsubscribe();
            }
        })
                .subscribeOn(Schedulers.newThread())//指定请求事件所在的线程(新线程)
                .observeOn(AndroidSchedulers.mainThread())//指定响应请求事件所在的线程(android 主线程)
                .subscribe(new Subscriber<Map<String, String>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("xx", "==map====onCompleted=====");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xx", "=map===onError=====" + e);
                    }

                    @Override
                    public void onNext(Map<String, String> map) {
                        textView.setText(map.get("1"));
                        String name = Thread.currentThread().getName();
                        Log.d("xx", "currentThread======" + name);//main
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d("xx", "==map====onStart=====");
                    }


                });


    }

    /**
     * Observable可以是一个数据库查询，Subscriber用来显示查询结果；
     * Observable可以是屏幕上的点击事件，Subscriber用来响应点击事件；
     * Observable可以是一个网络请求，Subscriber用来显示请求结果。

     */
    public void mapAndcommonButton(){

        Observable<String> observa = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("rain");
            }
        });


        Subscriber<String> subscriber = new Subscriber<String>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("xx", "==3=onNext====="+s);
            }
        };

        observa.subscribe(subscriber);

    }


}
