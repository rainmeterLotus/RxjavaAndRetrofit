package com.ccdt.live.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author malong
 * @Package com.ccdt.live.datadroid.model
 * @Description: 导航服务器返回的服务地址
 * @date 2015/12/22 11:11
 */
public class ServerUrlInfo implements Parcelable {
//    {
//            "code": "200",//  1001 签名校验失败; 1003 服务器错误;  200   正确返回
//            "result": "",//   返回值说明, 返回200时不做任何说明,result为空
//            "commServer": "test.douniuonline.com",//通用服务
//            "commPort": "8005",
//            "searchServer": "test.douniuonline.com",//搜索服务
//            "searchPort": "8004",
//            "chatroomServer": "test.douniuonline.com",//融云服务
//            "chatroomPort": "8006",
//            "mmServer": "http://test.douniuonline.com:25416/mmserver",//mmServer
//            "duration": 36000//地址有效时间 单位秒
//    }

    private String code;
    private String result;
    private String commServer;
    private String commPort;
    private String searchServer;
    private String searchPort;
    private String chatroomServer;
    private String chatroomPort;
    private String mmServer;
    private long duration;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCommServer() {
        return commServer;
    }

    public void setCommServer(String commServer) {
        this.commServer = commServer;
    }

    public String getCommPort() {
        return commPort;
    }

    public void setCommPort(String commPort) {
        this.commPort = commPort;
    }

    public String getSearchServer() {
        return searchServer;
    }

    public void setSearchServer(String searchServer) {
        this.searchServer = searchServer;
    }

    public String getSearchPort() {
        return searchPort;
    }

    public void setSearchPort(String searchPort) {
        this.searchPort = searchPort;
    }

    public String getChatroomServer() {
        return chatroomServer;
    }

    public void setChatroomServer(String chatroomServer) {
        this.chatroomServer = chatroomServer;
    }

    public String getChatroomPort() {
        return chatroomPort;
    }

    public void setChatroomPort(String chatroomPort) {
        this.chatroomPort = chatroomPort;
    }

    public String getMmServer() {
        return mmServer;
    }

    public void setMmServer(String mmServer) {
        this.mmServer = mmServer;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    protected ServerUrlInfo(Parcel in) {
        code = in.readString();
        result = in.readString();
        commServer = in.readString();
        commPort = in.readString();
        searchServer = in.readString();
        searchPort = in.readString();
        chatroomServer = in.readString();
        chatroomPort = in.readString();
        mmServer = in.readString();
        duration = in.readLong();
    }

    public static final Creator<ServerUrlInfo> CREATOR = new Creator<ServerUrlInfo>() {
        @Override
        public ServerUrlInfo createFromParcel(Parcel in) {
            return new ServerUrlInfo(in);
        }

        @Override
        public ServerUrlInfo[] newArray(int size) {
            return new ServerUrlInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(result);
        dest.writeString(commServer);
        dest.writeString(commPort);
        dest.writeString(searchServer);
        dest.writeString(searchPort);
        dest.writeString(chatroomServer);
        dest.writeString(chatroomPort);
        dest.writeString(mmServer);
        dest.writeLong(duration);
    }

    @Override
    public String toString() {
        return "ServerUrlInfo{" +
                "code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", commServer='" + commServer + '\'' +
                ", commPort='" + commPort + '\'' +
                ", searchServer='" + searchServer + '\'' +
                ", searchPort='" + searchPort + '\'' +
                ", chatroomServer='" + chatroomServer + '\'' +
                ", chatroomPort='" + chatroomPort + '\'' +
                ", mmServer='" + mmServer + '\'' +
                ", duration=" + duration +
                '}';
    }
}
