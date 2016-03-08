package com.ccdt.live.rxjava;

import android.content.Context;
import android.os.Bundle;

import com.ccdt.live.port.RxjavaInterface;
import com.ccdt.ott.common.service.OttCommActionService;
import com.ccdt.ott.rongcloud.service.OttRongCloudService;
import com.ccdt.ott.search.service.OttSearchService;

import java.util.ArrayList;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by rain on 2016/1/4.
 */
public class RxRequest {
    private Context context;
    private static RxRequest instance;

    private static OttCommActionService commS = new OttCommActionService("test.douniuonline.com",8005);
    private static OttSearchService serarchS = new OttSearchService("test.douniuonline.com",8004);
    private static OttRongCloudService rongCloudService = new OttRongCloudService("test.douniuonline.com",8006);

    private RxRequest(Context context){
        this.context = context;
    }

    public static RxRequest getInstance(Context context){
        if(instance == null){
            //双重判断为了消除安全隐患，没有将synchronized放在方法上是因为双重判断可以减少读锁的次数。
            synchronized (RxRequest.class){
                if (instance == null){
                    instance = new RxRequest(context);
                }
            }
        }
        return instance;
    }


    /**
     *
     * @Description: 直播使用
     * @param params
     * @return
     * @return Map<String,String>
     * @author wangdx
     * @date 2015-6-18下午4:38:45
     */
    public void liveOprate(int requestCode,RxjavaInterface rxjavaInterface,final Map<String, String> params) {
        Map<String, String> map = commS.liveOprate(params);

        Observable.create(new Observable.OnSubscribe<Map<String, String>>() {
            @Override
            public void call(Subscriber<? super Map<String, String>> subscriber) {
                subscriber.onNext(commS.liveOprate(params));
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<Map<String, String>>() {
            @Override
            public void onCompleted() {
                if(rxjavaInterface != null){
                    rxjavaInterface.onRxjavaComplete(requestCode);
                }
            }

            @Override
            public void onError(Throwable e) {
                if(rxjavaInterface != null){
                    rxjavaInterface.onRxjavaError(e);
                }
            }

            @Override
            public void onNext(Map<String, String> map) {
                if(rxjavaInterface != null){
                    ArrayList list = new ArrayList();
                    list.add(map);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(requestCode+"",list);
                    rxjavaInterface.onRxjavaSuccess(requestCode, bundle);
                }
            }
        });
    }


    private Map<String, String> rxLive(final Map<String, String> params) {



        return null;
    }
}


