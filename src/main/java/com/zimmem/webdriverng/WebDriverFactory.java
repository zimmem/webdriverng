package com.zimmem.webdriverng;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

/**
 * WebDriver 工厂类
 * 
 * @author zhaowen.zhuangzw 2011-12-26 下午2:06:49
 */
public class WebDriverFactory {

    private static List<WebDriverBuilder> builders      = new ArrayList<WebDriverBuilder>();

    private static WebDriverConfig        defaultConfig = null;

    private static ThreadLocal<WebDriver> threadCache   = new ThreadLocal<WebDriver>();

    static {
        init();
    }

    /**
     * 获取已缓存的当前线各程 WebDriver， 如果未创建，则创建一个新的 driver;
     * 
     * @return
     */
    public static WebDriver getCurrentWebDriver() {
        return getCurrentWebDriver(true);
    }

    /**
     * 获取已缓存的当前线各程 WebDriver
     * 
     * @param createForce 如果当前线程未创建WebDriver，是否强制创建一个新的 driver
     * @return
     */
    public static WebDriver getCurrentWebDriver(boolean createForce) {
        WebDriver webDriver = threadCache.get();
        if (webDriver == null && createForce) {
            webDriver = createWebDriver(defaultConfig);
            threadCache.set(webDriver);
        }
        return webDriver;
    }

    private static WebDriver createWebDriver(WebDriverConfig config) {
        if (config != null) {
            for (WebDriverBuilder builder : builders) {
                if (builder.apply(config)) {
                    WebDriver driver = builder.buildWebDriver(config);
                    driver.manage().timeouts().implicitlyWait(config.getWait() > 0 ? config.getWait() : 30,
                                                              TimeUnit.SECONDS);
                    return driver;
                }
            }
        }
        throw new RuntimeException("can't create webdriver with the config:" + config);

    }

    public static void clear() {
        threadCache.remove();
    }

    /**
     * 
     */
    private static void init() {
        registerBuilder(new RemoteWebDriverBuilder());
        registerBuilder(new IEWebDriverBuilder());
        registerBuilder(new ChromeDriverBuilder());
        registerBuilder(new FirefoxDriverBuilder());
        loadDefaultConfig();
    }

    /**
     * 
     */
    private static void loadDefaultConfig() {
        defaultConfig = WebDriverConfigLoader.getInstance().load();

    }

    private static void registerBuilder(WebDriverBuilder builder) {
        builders.add(builder);
    }

}
