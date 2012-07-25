package com.zimmem.webdriverng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.ho.yaml.Yaml;

/**
 * 类YamlWebDriverLoader.java的实现描述：TODO 类实现描述
 * 
 * @author zhaowen.zhuangzw 2011-12-26 下午3:37:12
 */
public class YamlWebDriverConfigLoader extends WebDriverConfigLoader {

    @Override
    public WebDriverConfig load() {
        String yamlFile = System.getProperty("webdriver.config");
        WebDriverConfig config = null;
        try {
            if (yamlFile == null || "".equals(yamlFile)) {
                return loadDefaultConfig();
            } else {
                File configFile = new File(yamlFile);
                if (configFile.exists()) {
                    config = Yaml.loadType(configFile, WebDriverConfig.class);
                } else {
                    config = loadDefaultConfig();
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return config;

    }

    private WebDriverConfig loadDefaultConfig() throws FileNotFoundException {
        WebDriverConfig config = null;
        InputStream is = getClass().getClassLoader().getResourceAsStream("driver.yaml");
        config = Yaml.loadType(is, WebDriverConfig.class);
        return config;
    }

}
