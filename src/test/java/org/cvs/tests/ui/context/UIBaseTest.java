package org.cvs.tests.ui.context;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.cvs.utilities.AllureUtilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Level;

import static org.cvs.core.config.Config.WEB_HOST;

@Log4j2
public class UIBaseTest extends BaseTestDataPreparation {

    @BeforeSuite(alwaysRun = true)
    public void setUpBrowser() {
        Configuration.timeout = WebTimeouts.IMPLICIT_TIMEOUT;
        Configuration.pageLoadTimeout = WebTimeouts.PAGE_LOAD_TIMEOUT;
        Configuration.baseUrl = WEB_HOST;
        Configuration.headless = true;
        log.info("Browser configured");
    }

    @BeforeSuite(alwaysRun = true)
    public static void setUpAllure() {
        AllureSelenide allureSelenide = new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
                .enableLogs(io.qameta.allure.selenide.LogType.BROWSER, Level.SEVERE);
        SelenideLogger.addListener("AllureSelenide", allureSelenide);
        log.info("Allure configured");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        AllureUtilities.attachScreenshot();
        Selenide.closeWebDriver();
    }
}
