package com.ccdt.live.bean;

/**
 * Created by rain on 2015/12/28.
 */
public class WeatherData {
    private String desc;
    private String status;
    private WeatherData_to data;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WeatherData_to getData() {
        return data;
    }

    public void setData(WeatherData_to data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "desc='" + desc + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
