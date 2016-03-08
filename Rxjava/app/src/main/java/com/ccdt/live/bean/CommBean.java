package com.ccdt.live.bean;

/**
 * Created by rain on 2015/12/31.
 */
public class CommBean {

    private String userId;
    private String system;
    private String version;
    private String signFlag;

    public CommBean(String userId, String system, String version, String signFlag) {
        this.userId = userId;
        this.system = system;
        this.version = version;
        this.signFlag = signFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }
}
