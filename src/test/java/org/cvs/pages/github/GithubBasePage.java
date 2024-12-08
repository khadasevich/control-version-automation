package org.cvs.pages.github;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Data
public abstract class GithubBasePage {

    private SelenideElement progressBar = $("[class^='progress-pjax-loader']");
    private SelenideElement pageNotFoundError = $("[alt^='404']");

    public SelenideElement getElementByTitle(String title) {
        String xpath = String.format("//*[@title='%s']", title);
        return $x(xpath);
    }
}
