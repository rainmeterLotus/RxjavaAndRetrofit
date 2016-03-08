package com.ccdt.live.bean;

import java.util.List;

/**
 * Created by rain on 2015/12/28.
 */
public class WeatherData_to {
    private String wendu;
    private String ganmao;
    private List<WeatherForecast> forecast;
    private WeatherYesterday yesterday;
    private String aqi;
    private String city;

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public List<WeatherForecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherForecast> forecast) {
        this.forecast = forecast;
    }

    public WeatherYesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(WeatherYesterday yesterday) {
        this.yesterday = yesterday;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "WeatherData_to{" +
                "wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", forecast=" + forecast +
                ", yesterday=" + yesterday +
                ", aqi='" + aqi + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
