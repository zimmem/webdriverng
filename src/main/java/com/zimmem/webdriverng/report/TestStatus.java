package com.zimmem.webdriverng.report;

import org.testng.ITestResult;

/**
 * @author zhaowen.zhuangzw 2012-2-18 下午11:01:38
 */
public enum TestStatus {

    success, failure, skip;

    public static TestStatus getStatus(int testStatus) {
        return ITestResult.SUCCESS == testStatus ? success : (ITestResult.FAILURE == testStatus ? failure : skip);
    }

}
