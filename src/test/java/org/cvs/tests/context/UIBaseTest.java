package org.cvs.tests.context;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.cvs.steps.ui.UISteps;
import org.cvs.utilities.AllureReportUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.Cookie;

import java.util.logging.Level;

import static org.cvs.core.config.Config.WEB_HOST;
import static org.cvs.core.config.Config.getUISteps;

@Log4j2
public class UIBaseTest extends VcsMgmtPreconditions {

    protected static Cookie userSession;
    protected static final ThreadLocal<UISteps> uiSteps = new ThreadLocal<>();

    @BeforeSuite
    public void uiTestsSetUp() {
        setUpAllure();
        setUpBrowser();
    }

    private void setUpAllure() {
        AllureSelenide allureSelenide = new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
                .enableLogs(io.qameta.allure.selenide.LogType.BROWSER, Level.SEVERE);
        SelenideLogger.addListener("AllureSelenide", allureSelenide);
        log.info("Allure configured");
    }

    private void setUpBrowser() {
        Configuration.timeout = WebTimeouts.IMPLICIT_TIMEOUT;
        Configuration.pageLoadTimeout = WebTimeouts.PAGE_LOAD_TIMEOUT;
        Configuration.baseUrl = WEB_HOST;
        log.info("Browser configured");
    }

    @BeforeMethod
    public void loginAndGetCookie() {
        uiSteps.set(getUISteps());
        userSession = uiSteps.get().logIn();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        AllureReportUtility.attachScreenshot();
        Selenide.closeWebDriver();
    }
}
