package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseTest;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();
        BaseTest base = (BaseTest) testClass;

        ScreenshotUtil.capture(base.driver, result.getName());
    }
}