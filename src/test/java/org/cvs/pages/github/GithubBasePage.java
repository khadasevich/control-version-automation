package org.cvs.pages.github;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Data
public abstract class GithubBasePage {

    private SelenideElement progressBar = $("[class^='progress-pjax-loader']");
    private SelenideElement pageNotFoundError = $("[alt^='404']");

    public SelenideElement getElementByTitle(String title) {
        String xpath = String.format("//*[@title='%s']", title);
        return $$x(xpath).filter(visible).first();
    }

    public boolean pollPageUntilElementIsVisible(SelenideElement element) {
        if (element.isDisplayed()) {
            return true;
        } else {
            refresh();
            return element.isDisplayed();
        }
    }
}
