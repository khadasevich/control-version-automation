package org.cvs.tests.context;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.cvs.steps.ui.UISteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.Cookie;

import java.util.logging.Level;

import static org.cvs.core.config.Config.WEB_HOST;
import static org.cvs.core.config.Config.getUISteps;

@Log4j2
public class UITest extends VcsMgmtPreconditions {

    private UISteps uiSteps;
    private Cookie userSession;

    @BeforeSuite
    public void uiTestsSetUp() {
        setUpAllure();
        setUpBrowser();
        uiSteps = getUISteps();
        userSession = uiSteps.logIn();
    }

    private void setUpAllure() {
        AllureSelenide allureSelenide = new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
                .enableLogs(io.qameta.allure.selenide.LogType.BROWSER, Level.SEVERE);
        SelenideLogger.addListener("AllureSelenide", allureSelenide);
    }

    private void setUpBrowser() {
        log.info("Configure browser before start");
        Configuration.timeout = WebTimeouts.IMPLICIT_TIMEOUT;
        Configuration.pageLoadTimeout = WebTimeouts.PAGE_LOAD_TIMEOUT;
        Configuration.headless = false;
        Configuration.baseUrl = WEB_HOST;
    }


    @BeforeMethod
    public void loginForUITests() {
        //ToDo: add login via
    }
}
