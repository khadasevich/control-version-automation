package org.cvs.pages.github;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;

@Data
public class GithubLoginPage extends GithubBasePage {

    public static final String LOGIN_PAGE_PATH = "/login";
    private SelenideElement usernameInput = $(id("login_field"));
    private SelenideElement passwordInput = $(id("password"));
    private SelenideElement signInButton = $(name("commit"));
    private SelenideElement homePageIcon = $("[aria-label^='Homepage']");
}
