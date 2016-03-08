package com.ccdt.live.activity;

import android.util.Log;
import android.view.View;


import com.ccdt.live.R;
import com.ccdt.live.base.BaseActivity;
import com.ccdt.live.bean.FeedBack;
import com.ccdt.live.bean.ServerUrlInfo;
import com.ccdt.live.bean.WeatherData;
import com.ccdt.live.port.MyService;
import com.ccdt.live.util.PaySignUtil;
import com.squareup.okhttp.ResponseBody;

import java.util.HashMap;

import butterknife.OnClick;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 该类主要讲解retrofit的用法,以及retrofit结合rxjava使用
 */
public class RetrofitActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_retrofit;
    }

    @OnClick({R.id.button_post_comm})
    public void retrofitPost(View view){
        switch (view.getId()) {
            case R.id.button_post_comm: {
                postComm();
                break;
            }


        }
    }




    @OnClick({R.id.button_github,R.id.button_github_observable,R.id.button_weather,R.id.button_weather_observable,R.id.button_comm,R.id.button_comm_observable})
    public void regtrofitGet(View view){
       switch (view.getId()){
           case R.id.button_github:{
               gitHub();
               break;
           }
           case R.id.button_github_observable:{
               gitHubObser();
               break;
           }
           case R.id.button_weather:{
               getWeather();
               break;
           }
           case R.id.button_weather_observable:{
               getWeatherObser();
               break;
           }

           case R.id.button_comm:{
               getVers();
               break;
           }

           case R.id.button_comm_observable:{
               getVersObser();
               break;
           }
       }

    }




    /**
     * 形如https://api.github.com/users/rainmeter
     */
    private void gitHub() {
        String API_hub = "https://api.github.com/";  // BASE URL
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_hub).addConverterFactory(GsonConverterFactory.create()).build();

        MyService myService = retrofit.create(MyService.class);
        Call<FeedBack> feed = myService.getFeed("rainmeterLotus");
        feed.enqueue(new Callback<FeedBack>() {
            @Override
            public void onResponse(Response<FeedBack> response, Retrofit retrofit) {
                Log.d("xx", response.body() + "====getEvents_url====" + response.body().getEvents_url() + "=========success==" + response.toString());

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("xx", "=========onError==" + t.getMessage());
            }
        });
    }

    /**
     * 形如https://api.github.com/users/rainmeter
     * 并且带有rxjava处理
     */
    private void gitHubObser() {
        String API_hub = "https://api.github.com/";  // BASE URL
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_hub).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        MyService myService = retrofit.create(MyService.class);
        Observable<FeedBack> feedObser = myService.getFeedObser("rainmeterLotus");
        Log.d("xx", "==Observable<FeedBack>=======feedObser==" + feedObser.getClass().getName());
        feedObser.subscribeOn(Schedulers.newThread())//必须指定请求事件的线程，而且不能是主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FeedBack>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xx", "==Observable<FeedBack>=======onError==" + e.getMessage());
                    }

                    @Override
                    public void onNext(FeedBack feedBack) {
                        Log.d("xx", "====Observable<FeedBack>====" + feedBack.getEvents_url() + "===== feedBack.toString=====" + feedBack.toString());
                    }
                });
    }



    /**
     * 形如http://wthrcdn.etouch.cn/weather_mini？citykey=101010100
     */
    private void getWeather() {
        String API_city = "http://wthrcdn.etouch.cn/";  // BASE URL
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_city).addConverterFactory(GsonConverterFactory.create()).build();
        MyService myService = retrofit.create(MyService.class);


        Call<WeatherData> weather = myService.getWeather("101010100");
        weather.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Response<WeatherData> response, Retrofit retrofit) {

                int code = response.code();
                if(response.isSuccess()){
                    Log.d("xx","==Call<WeatherData>==onResponse===isSuccess=====");
                }else{
                    ResponseBody responseBody = response.errorBody();
                    Log.d("xx","==Call<WeatherData>==onResponse===errorBody====="+responseBody);
                }

                WeatherData body = response.body();
                Log.d("xx","==code==="+code+ "==Call<WeatherData>==onResponse====" + body);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("xx", "====Call<WeatherData>=====onError==" + t.getMessage());
            }
        });

    }


    /**
     * 形如http://wthrcdn.etouch.cn/weather_mini？citykey=101010100
     * 并且带有rxjava处理
     */
    private void getWeatherObser() {
        String API_city = "http://wthrcdn.etouch.cn/";  // BASE URL
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_city).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        MyService myService = retrofit.create(MyService.class);

        Observable<WeatherData> weatherObser = myService.getWeatherObser("101010100");
        weatherObser.subscribeOn(Schedulers.newThread())//必须指定请求事件的线程，而且不能是主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xx", "==Observable<WeatherData>=======onError==" + e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        Log.d("xx", "==Observable<WeatherData>==getEvents_url====" + weatherData);
                    }
                });

    }

    private void postComm() {
        String API_comm = "http://10.10.0.237:8080/";  // BASE URL

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_comm).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        MyService myService = retrofit.create(MyService.class);
        HashMap params = new HashMap<>();
        params.put("userId","1");
        params.put("system","android");
        params.put("version","1.6.0");
        Call<ServerUrlInfo> postComm = myService.postComm("1", "android", "1.6.0",
                PaySignUtil.sign(params, "ECOW*^#dHOV3N#)*%$N556IVD#*&A9"));
        postComm.enqueue(new Callback<ServerUrlInfo>() {
            @Override
            public void onResponse(Response<ServerUrlInfo> response, Retrofit retrofit) {
                ServerUrlInfo body = response.body();
                Log.d("xx", "=post==== Call<ServerUrlInfo>=====666==onResponse==" + body.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("xx", "====post===== Call<ServerUrlInfo>==onFailure====" + t.getMessage());
            }
        });
    }
    /**
     * 形如http://10.10.6.25:9001/mmserver/getServerAddr.do?userId=1&system=android&version=1.6.0&signFlag=7B8758776018753534F20BD269A2FB1F
     */
    private void getVers() {
        String API_comm = "http://10.10.0.237:8080/";  // BASE URL

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_comm).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        MyService myService = retrofit.create(MyService.class);

        HashMap params = new HashMap<>();
        params.put("userId","1");
        params.put("system","android");
        params.put("version","1.6.0");
        params.put("signFlag", PaySignUtil.sign(params, "ECOW*^#dHOV3N#)*%$N556IVD#*&A9"));

        Call<ServerUrlInfo> comm = myService.getComm(params);
        comm.enqueue(new Callback<ServerUrlInfo>() {
            @Override
            public void onResponse(Response<ServerUrlInfo> response, Retrofit retrofit) {
                ServerUrlInfo body = response.body();
                Log.d("xx", "== Call<ServerUrlInfo>=======onResponse==" + body.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("xx", "== Call<ServerUrlInfo>==onFailure====" + t.getMessage());
            }
        });
    }

    /**
     * 形如http://10.10.6.25:9001/mmserver/getServerAddr.do?userId=1&system=android&version=1.6.0&signFlag=7B8758776018753534F20BD269A2FB1F
     * 并且带有rxjava处理
     */
    private void getVersObser() {
        String API_comm = "http://10.10.6.25:9001/mmserver/";  // BASE URL

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_comm).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        MyService myService = retrofit.create(MyService.class);

        HashMap params = new HashMap<>();
        params.put("userId","1");
        params.put("system","android");
        params.put("version","1.6.0");
        params.put("signFlag", PaySignUtil.sign(params, "ECOW*^#dHOV3N#)*%$N556IVD#*&A9"));


        Observable<ServerUrlInfo> commObser = myService.getCommObser(params);

        commObser.subscribeOn(Schedulers.newThread())//必须指定请求事件的线程，而且不能是主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ServerUrlInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("xx", "==Observable<ServerUrlInfo>==onFailure====" + e.getMessage());
                    }

                    @Override
                    public void onNext(ServerUrlInfo serverUrlInfo) {
                        Log.d("xx", "==Observable<ServerUrlInfo>=======onResponse==" + serverUrlInfo.toString());
                    }
                });


    }



}
