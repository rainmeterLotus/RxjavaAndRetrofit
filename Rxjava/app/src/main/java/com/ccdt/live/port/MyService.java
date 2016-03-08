package com.ccdt.live.port;

import com.ccdt.live.bean.Course;
import com.ccdt.live.bean.FeedBack;
import com.ccdt.live.bean.ServerUrlInfo;
import com.ccdt.live.bean.WeatherData;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by rain on 2015/12/25.
 */
public interface MyService {
    @GET("/users/{user}/repos")
    List<Course> listRepos(@Path("user") String user);

    @GET("users/{user}")
    Call<FeedBack> getFeed(@Path("user") String user);


    @GET("users/{user}")
    Observable<FeedBack> getFeedObser(@Path("user") String user);

    @GET("weather_mini")
    Call<WeatherData> getWeather(@Query("citykey") String key);

    @GET("weather_mini")
    Observable<WeatherData> getWeatherObser(@Query("citykey") String key);


    @FormUrlEncoded
    @POST("getServerAddr.do")
    Call<ServerUrlInfo> postComm(@Field("userId") String userId, @Field("system") String system,
                                 @Field("version") String version, @Field("signFlag") String signFlag);

    @GET("getServerAddr.do")
    Call<ServerUrlInfo> getComm(@QueryMap Map<String, String> params);

    @GET("getServerAddr.do")
    Observable<ServerUrlInfo> getCommObser(@QueryMap Map<String, String> params);
}
