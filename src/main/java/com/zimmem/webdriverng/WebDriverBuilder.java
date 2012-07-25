package com.zimmem.webdriverng;

import org.openqa.selenium.WebDriver;

/**
 * WebDriver构建器
 * 
 * @author zhaowen.zhuangzw 2011-12-26 下午2:07:06
 */
public interface WebDriverBuilder {

    /**
     * 检查是否适应该构造器
     * 
     * @param config
     * @return
     */
    boolean apply(WebDriverConfig config);

    /**
     * 构建WebDriver
     * 
     * @param config
     * @return
     */
    WebDriver buildWebDriver(WebDriverConfig config);
}
