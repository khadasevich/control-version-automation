package org.cvs.steps.ui.github;

import com.codeborne.selenide.WebDriverRunner;
import org.cvs.pages.github.GithubLoginPage;
import org.cvs.steps.ui.UISteps;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.cvs.core.config.Config.*;
import static org.cvs.pages.github.GithubLoginPage.LOGIN_PATH;

public class GitHubUISteps implements UISteps {

    @Override
    public Cookie logIn() {
        GithubLoginPage githubLoginPage = new GithubLoginPage();
        open(LOGIN_PATH);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        githubLoginPage.getUsernameInput().shouldBe(visible).setValue(USERNAME);
        githubLoginPage.getPasswordInput().shouldBe(visible).setValue(PASSWORD);
        githubLoginPage.getSignInButton().shouldBe(enabled).click();
        githubLoginPage.getHomePageIcon().shouldBe(enabled);
        return WebDriverRunner.getWebDriver().manage().getCookieNamed("user_session");
    }

    @Override
    public void createBranch() {

    }

    @Override
    public void addCommit() {

    }

    @Override
    public void createMergeRequest() {

    }

    @Override
    public void mergeMergeRequest() {

    }
}
