package org.cvs.steps.ui.github;

import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j2;
import org.cvs.pages.github.GithubBranchesPage;
import org.cvs.pages.github.GithubLoginPage;
import org.cvs.steps.ui.UISteps;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.cvs.core.config.Config.*;
import static org.cvs.pages.github.GithubBranchesPage.BRANCHES_PAGE_PATH;
import static org.cvs.pages.github.GithubLoginPage.LOGIN_PAGE_PATH;
import static org.cvs.tests.context.WebTimeouts.EXPLICIT_TIMEOUT;

@Log4j2
public class GitHubUISteps implements UISteps {

    @Override
    public Cookie logIn() {
        GithubLoginPage githubLoginPage = new GithubLoginPage();
        open(LOGIN_PAGE_PATH);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        githubLoginPage.getUsernameInput().shouldBe(visible).setValue(USERNAME);
        githubLoginPage.getPasswordInput().shouldBe(visible).setValue(PASSWORD);
        githubLoginPage.getSignInButton().shouldBe(enabled).click();
        githubLoginPage.getHomePageIcon().shouldBe(enabled);
        return WebDriverRunner.getWebDriver().manage().getCookieNamed("user_session");
    }

    @Override
    public void openRepositoryDetails() {
        open(String.format("/%s/%s", USERNAME, DEFAULT_REPOSITORY_NAME));
    }

    @Override
    public void createBranch(String branchName) {
        GithubBranchesPage githubBranchesPage = new GithubBranchesPage();
        open(String.format("/%s/%s/%s", USERNAME, DEFAULT_REPOSITORY_NAME, BRANCHES_PAGE_PATH));
        githubBranchesPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        GithubBranchesPage branchesPage = new GithubBranchesPage();
        branchesPage.getNewBranchButton().shouldBe(enabled).click();
        branchesPage.getBranchNameInput().shouldBe(visible).sendKeys(branchName);
        branchesPage.getCreateNewBranchButton().shouldBe(enabled).click();
        githubBranchesPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        log.info("Branch created");
    }

    @Override
    public void validateBranchCreation(String branchName) {
        GithubBranchesPage branchesPage = new GithubBranchesPage();
        branchesPage.getElementByTitle(branchName).shouldBe(visible, EXPLICIT_TIMEOUT).click();
        branchesPage.getPageNotFoundError().shouldNotBe(visible);
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
