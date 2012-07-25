package com.zimmem.webdriverng;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * WebDriver 配置
 * 
 * @author zhaowen.zhuangzw 2011-12-26 下午2:11:40
 */
public class WebDriverConfig {

    private String  platform;

    private String  browser;

    private String  version;

    private boolean remote;

    private String  hubUrl;

    private int     wait;

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the browser
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * @param browser the browser to set
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the remote
     */
    public boolean isRemote() {
        return remote;
    }

    /**
     * @param remote the remote to set
     */
    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    /**
     * @return the hubUrl
     */
    public String getHubUrl() {
        return hubUrl;
    }

    /**
     * @param hubUrl the hubUrl to set
     */
    public void setHubUrl(String hubUrl) {
        this.hubUrl = hubUrl;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the wait
     */
    public int getWait() {
        return wait;
    }

    /**
     * @param wait the wait to set
     */
    public void setWait(int wait) {
        this.wait = wait;
    }

}
