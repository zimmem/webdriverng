package com.zimmem.webdriverng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.IResultListener;

import com.zimmem.webdriverng.report.RuntimeInfoSection;
import com.zimmem.webdriverng.report.ScreenshotSection;
import com.zimmem.webdriverng.report.SectionCollector;

/**
 * 用于在测试失败时截图
 * 
 * @author zhaowen.zhuangzw 2011-12-28 下午1:51:38
 */
public class WebDriverListener implements IResultListener {

    /*
     * (non-Javadoc)
     * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
     */
    @Override
    public void onTestStart(ITestResult result) {
        // WebDriverFactory.createWebDriver();

    }

    /*
     * (non-Javadoc)
     * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        closeDriver();

    }

    /*
     * (non-Javadoc)
     * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
     */
    @Override
    public void onTestFailure(ITestResult result) {

        try {

            WebDriver driver = WebDriverFactory.getCurrentWebDriver(false);
            if (driver == null) {
                return;
            }
            String screenshot = tackScreenshot(driver, OutputType.BASE64);
            ScreenshotSection section = new ScreenshotSection();
            section.addScreenshot(screenshot);
            SectionCollector.collectSection(result, section);

            RuntimeInfoSection infoSection = new RuntimeInfoSection();
            infoSection.setCurrentUrl(driver.getCurrentUrl());
            infoSection.setPageSource(driver.getPageSource());
            SectionCollector.collectSection(result, infoSection);

            closeDriver();
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.log("Couldn't create screenshot");
            Reporter.log(e.getMessage());
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        closeDriver();

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        closeDriver();

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    /*
     * (non-Javadoc)
     * @see org.testng.internal.IConfigurationListener#onConfigurationSuccess(org.testng.ITestResult)
     */
    @Override
    public void onConfigurationSuccess(ITestResult itr) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see org.testng.internal.IConfigurationListener#onConfigurationFailure(org.testng.ITestResult)
     */
    @Override
    public void onConfigurationFailure(ITestResult itr) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onConfigurationSkip(ITestResult itr) {
        // TODO Auto-generated method stub

    }

    private void closeDriver() {
        WebDriver driver = WebDriverFactory.getCurrentWebDriver(false);
        if (driver == null) {
            return;
        }
        driver.quit();
        WebDriverFactory.clear();
    }

    private <X> X tackScreenshot(WebDriver driver, OutputType<X> type) {
        if (!(driver instanceof TakesScreenshot)) {
            driver = new Augmenter().augment(driver);
        }
        X screenshot = ((TakesScreenshot) driver).getScreenshotAs(type);
        return screenshot;
    }

}
