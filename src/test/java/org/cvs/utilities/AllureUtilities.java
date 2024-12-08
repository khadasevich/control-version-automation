package org.cvs.utilities;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


@Log4j2
public class AllureUtilities {

    public static final String UI_ALLURE_EPIC = "VCS UI TESTS";
    public static final String API_ALLURE_EPIC = "VCS API TESTS";

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }
        return null;
    }
}
