package com.ccdt.live.bean;
/**
 * @Package com.ccdt.news.data.model
 * @ClassName: WeatherInfo
 * @Description: 存储天气的对象,包括温度,时间,风向等
 * @author wangdx
 * @date 2015-2-4 下午5:59:56
 */

public class WeatherInfo {
    private String temperatureHight;
    private String temperatureLow;
    private String windLevel;
    private String windDirect;
    private String date;
    private String type;
    /**
     * @Description: 构造一个天气对象的信息
     * @param @param temperatureHight
     * @param @param temperatureLow
     * @param @param windLevel
     * @param @param windDirect
     * @param @param date
     * @param @param type    
     * @author wangdx
     * @date 2015-2-4下午6:04:14
    */ 
    public WeatherInfo(String temperatureHight, String temperatureLow, String windLevel,
            String windDirect, String date, String type) {
        super();
        this.temperatureHight = temperatureHight;
        this.temperatureLow = temperatureLow;
        this.windLevel = windLevel;
        this.windDirect = windDirect;
        this.date = date;
        this.type = type;
    }
    /**
     * @return the temperatureHight
     */
    public String getTemperatureHight() {
        return temperatureHight;
    }
    /**
     * @param temperatureHight the temperatureHight to set
     */
    public void setTemperatureHight(String temperatureHight) {
        this.temperatureHight = temperatureHight;
    }
    /**
     * @return the temperatureLow
     */
    public String getTemperatureLow() {
        return temperatureLow;
    }
    /**
     * @param temperatureLow the temperatureLow to set
     */
    public void setTemperatureLow(String temperatureLow) {
        this.temperatureLow = temperatureLow;
    }
    /**
     * @return the windLevel
     */
    public String getWindLevel() {
        return windLevel;
    }
    /**
     * @param windLevel the windLevel to set
     */
    public void setWindLevel(String windLevel) {
        this.windLevel = windLevel;
    }
    /**
     * @return the windDirect
     */
    public String getWindDirect() {
        return windDirect;
    }
    /**
     * @param windDirect the windDirect to set
     */
    public void setWindDirect(String windDirect) {
        this.windDirect = windDirect;
    }
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
}
