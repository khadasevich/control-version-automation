package org.cvs.core.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info(">>>>>>>> Start of the {} test", result.getName());
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("<<<<<<<< Test {} passed", result.getName());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("<<<<<<<< Test {} skipped", result.getName());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        log.info("<<<<<<<< Test {} failed", result.getName());
    }
}
