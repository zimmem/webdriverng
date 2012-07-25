package com.zimmem.webdriverng;

/**
 * 加载 WebDriver 配置
 * 
 * @author zhaowen.zhuangzw 2011-12-26 下午3:34:09
 */
public abstract class WebDriverConfigLoader {

    public static WebDriverConfigLoader getInstance() {
        return new YamlWebDriverConfigLoader();
    }

    public abstract WebDriverConfig load();
}
