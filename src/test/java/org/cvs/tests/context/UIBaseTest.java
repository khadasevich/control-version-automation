package org.cvs.tests.context;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.cvs.utilities.AllureUtilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.util.logging.Level;

import static org.cvs.core.config.Config.WEB_HOST;

@Log4j2
public class UIBaseTest extends BaseTestDataPreparation {

    @BeforeTest(alwaysRun = true)
    public void setUpBrowser() {
        Configuration.timeout = WebTimeouts.IMPLICIT_TIMEOUT;
        Configuration.pageLoadTimeout = WebTimeouts.PAGE_LOAD_TIMEOUT;
        Configuration.baseUrl = WEB_HOST;
        Configuration.headless = true;
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        Configuration.browserCapabilities = options;
        log.info("Browser configured");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        AllureUtilities.attachScreenshot();
        Selenide.closeWebDriver();
    }
}
