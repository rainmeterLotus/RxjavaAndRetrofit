package com.ccdt.live.bean;

/**
 * Created by rain on 2015/12/28.
 */
public class FeedBack {
    private String name;
    private String blog;
    private String company;
    private String events_url;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                ", company='" + company + '\'' +
                ", events_url='" + events_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
