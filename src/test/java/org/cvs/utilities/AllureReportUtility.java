package org.cvs.utilities;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureReportUtility {

    public static final String UI_ALLURE_EPIC = "VCS UI TESTS";

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }
        return null;
    }
}
