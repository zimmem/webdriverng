package com.zimmem.webdriverng;

/**
 * 通过浏览器名称判断浏览器
 * 
 * @author zhaowen.zhuangzw 2011-12-26 下午2:58:05
 */
public class BroswerChecker {

    public static boolean isIE(String broswer) {
        return broswer != null && (broswer.contains("internet explorer") || broswer.contains("iexplorer"));
    }

    public static boolean isFirefox(String broswer) {
        return broswer != null && broswer.contains("firefox");
    }

    public static boolean isChrome(String broswer) {
        return broswer != null && broswer.contains("chrome");
    }
}
