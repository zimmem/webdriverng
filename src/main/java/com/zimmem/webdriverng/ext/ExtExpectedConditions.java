package com.zimmem.webdriverng.ext;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExtExpectedConditions {

    private final static Logger log = Logger.getLogger(ExtExpectedConditions.class.getName());

    private ExtExpectedConditions() {
        // Utility class
    }

    /**
     * click until success
     * 
     * @param by
     * @return
     */
    public static ExpectedCondition<WebElement> clickSuccess(final By by) {
        return new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = findElement(by, driver);
                try {
                    element.click();
                    return element;
                } catch (Exception e) {
                    log.log(Level.WARNING, e.getMessage(), e);
                    return null;
                }
            }

        };

    }

    /**
     * Looks up an element. Logs and re-throws WebDriverException if thrown.
     * <p/>
     * Method exists to gather data for http://code.google.com/p/selenium/issues/detail?id=1800
     */
    private static WebElement findElement(By by, WebDriver driver) {
        try {
            return driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw e;
        } catch (WebDriverException e) {
            log.log(Level.WARNING, String.format("WebDriverException thrown by findElement(%s)", by), e);
            throw e;
        }
    }

    /**
     * @see #findElement(By, WebDriver)
     */
    private static List<WebElement> findElements(By by, WebDriver driver) {
        try {
            return driver.findElements(by);
        } catch (WebDriverException e) {
            log.log(Level.WARNING, String.format("WebDriverException thrown by findElement(%s)", by), e);
            throw e;
        }
    }
}
