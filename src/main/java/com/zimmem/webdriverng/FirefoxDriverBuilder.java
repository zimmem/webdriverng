package com.zimmem.webdriverng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author zhaowen.zhuangzw 2011-12-28 下午8:21:59
 */
public class FirefoxDriverBuilder implements WebDriverBuilder {

    @Override
    public boolean apply(WebDriverConfig config) {
        return BroswerChecker.isFirefox(config.getBrowser());
    }

    @Override
    public WebDriver buildWebDriver(WebDriverConfig config) {
        DesiredCapabilities firefox = DesiredCapabilities.firefox();
        WebDriver webDriver = new FirefoxDriver(firefox);
        return webDriver;
    }

}
