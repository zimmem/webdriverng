package com.zimmem.webdriverng;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author zhaowen.zhuangzw 2011-12-26 下午2:41:44
 */
public class RemoteWebDriverBuilder implements WebDriverBuilder {

    @Override
    public boolean apply(WebDriverConfig config) {
        return config.isRemote();
    }

    @Override
    public WebDriver buildWebDriver(WebDriverConfig config) {
        RemoteWebDriver webdriver = null;
        try {
            webdriver = new RemoteWebDriver(new URL(config.getHubUrl()), createdDesiredCapabilities(config));
            webdriver.setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException e) {
            throw new RuntimeException("error occurred while create RemoteWebDriver with hub url :"
                                       + config.getHubUrl(), e);
        }
        return webdriver;
    }

    private DesiredCapabilities createdDesiredCapabilities(WebDriverConfig config) {
        DesiredCapabilities capabilities = null;
        if (BroswerChecker.isIE(config.getBrowser())) {
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        } else if (BroswerChecker.isFirefox(config.getBrowser())) {
            capabilities = DesiredCapabilities.firefox();
        } else if (BroswerChecker.isChrome(config.getBrowser())) {
            capabilities = DesiredCapabilities.chrome();
        }
        return capabilities;

    }
}
