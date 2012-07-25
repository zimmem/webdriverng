package com.zimmem.webdriverng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author zhaowen.zhuangzw 2011-12-28 下午8:21:59
 */
public class ChromeDriverBuilder implements WebDriverBuilder {

    @Override
    public boolean apply(WebDriverConfig config) {
        return BroswerChecker.isChrome(config.getBrowser());
    }

    @Override
    public WebDriver buildWebDriver(WebDriverConfig config) {
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        WebDriver webDriver = new ChromeDriver(chrome);
        return webDriver;
    }

}
