package com.ccdt.live.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rain on 2015/12/31.
 */
public class CityKey {
    @SerializedName("citykey")
    private String citykey;

    public CityKey(String citykey) {
        this.citykey = citykey;
    }

    public String getCitykey() {
        return citykey;
    }

    public void setCitykey(String citykey) {
        this.citykey = citykey;
    }
}
