package test.packag;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author zhaowen.zhuangzw 2012-2-18 下午2:19:19
 */
public class TestNGSample {

    int i = 0;

    @Test(invocationCount = 5)
    public void testMethod() {
        i++;
        Assert.assertTrue(i > 2);
    }

    @Test
    public void testMethod2() {
        throw new RuntimeException();
    }

    @DataProvider(name = "test")
    public Object[][] prepare() {
        return new Object[][] { { 1, 2 }, { 3, 4 } };
    }

    @Test(dataProvider = "test", description = "test sample")
    public void testMethod3(Integer i, Integer j) {
        if (i > 1) {
            throw new RuntimeException();
        }
    }

}
