package com.zimmem.webdriverng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 构建 IE WebDriver
 * 
 * @author zhaowen.zhuangzw 2011-12-26 下午2:09:29
 */
public class IEWebDriverBuilder implements WebDriverBuilder {

    @Override
    public boolean apply(WebDriverConfig config) {
        if (config.getBrowser() == null) return false;

        if (config.isRemote()) return false;

        return BroswerChecker.isIE(config.getBrowser());
    }

    @Override
    public WebDriver buildWebDriver(WebDriverConfig config) {
        DesiredCapabilities internetExplorer = DesiredCapabilities.internetExplorer();
        internetExplorer.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        WebDriver webDriver = new InternetExplorerDriver(internetExplorer);
        return webDriver;
    }

}
