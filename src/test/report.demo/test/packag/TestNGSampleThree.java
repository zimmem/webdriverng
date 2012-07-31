package test.packag;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.zimmem.webdriverng.WebDriverFactory;

/**
 * @author zhaowen.zhuangzw 2012-2-18 下午2:19:19
 */
public class TestNGSampleThree {

    @Test(description = "test sample")
    public void testMethod() {
        WebDriver currentWebDriver = WebDriverFactory.getCurrentWebDriver();
        currentWebDriver.get("http://www.google.com");
        throw new RuntimeException();
    }

    @Test
    public void testMethod2() {
        // throw new RuntimeException();
    }

    @DataProvider(name = "test")
    public Object[][] prepare() {
        return new Object[][] { { 1, 2 }, { 3, 4 } };
    }

    @Test(dataProvider = "test")
    public void testMethod3(Integer i, Integer j) {
        System.out.println(i + j);
    }

}
