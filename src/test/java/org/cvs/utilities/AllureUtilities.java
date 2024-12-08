package org.cvs.utilities;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.logging.Level;

@Log4j2
public class AllureUtilities {

    public static final String UI_ALLURE_EPIC = "VCS UI TESTS";

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }
        return null;
    }

    public static void setUpAllure() {
        AllureSelenide allureSelenide = new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
                .enableLogs(io.qameta.allure.selenide.LogType.BROWSER, Level.SEVERE);
        SelenideLogger.addListener("AllureSelenide", allureSelenide);
        log.info("Allure configured");
    }
}
