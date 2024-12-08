package org.cvs.steps.ui.github;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j2;
import org.cvs.pages.github.GithubBranchDetailsPage;
import org.cvs.pages.github.GithubBranchesPage;
import org.cvs.pages.github.GithubLoginPage;
import org.cvs.steps.ui.UISteps;
import org.cvs.utilities.PollerUtility;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.cvs.core.config.Config.*;
import static org.cvs.pages.github.GithubBranchesPage.BRANCHES_PAGE_PATH;
import static org.cvs.pages.github.GithubLoginPage.LOGIN_PAGE_PATH;
import static org.cvs.tests.context.WebTimeouts.EXPLICIT_TIMEOUT;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class GitHubUISteps implements UISteps {

    @Override
    public Cookie logIn() {
        open(LOGIN_PAGE_PATH);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        GithubLoginPage githubLoginPage = new GithubLoginPage();
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
        open(String.format("/%s/%s/%s", USERNAME, DEFAULT_REPOSITORY_NAME, BRANCHES_PAGE_PATH));
        GithubBranchesPage githubBranchesPage = new GithubBranchesPage();
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
        branchesPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        branchesPage.getPageNotFoundError().shouldNotBe(visible);
    }

    @Override
    public void addCommit(String branchName, String fileName) {
        open(String.format("/%s/%s/tree/%s", USERNAME, DEFAULT_REPOSITORY_NAME, branchName));
        GithubBranchDetailsPage branchDetailsPage = new GithubBranchDetailsPage();
        branchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        branchDetailsPage.getAddFileSelector().shouldBe(enabled).click();
        branchDetailsPage.getCreateNewFileButton().shouldBe(enabled).click();
        branchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        branchDetailsPage.getFileNameInput().sendKeys(fileName);
        branchDetailsPage.getOpenCommitDialogButton().shouldBe(enabled).click();
        branchDetailsPage.getSubmitCommitDialogButton().shouldBe(enabled).click();
        branchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
    }

    @Override
    public void validateCommitCreation(String branchName, String fileName) {
        open(String.format("/%s/%s/tree/%s", USERNAME, DEFAULT_REPOSITORY_NAME, branchName));
        GithubBranchDetailsPage branchDetailsPage = new GithubBranchDetailsPage();
        SelenideElement commitLink = branchDetailsPage.getElementByTitle(fileName);
        PollerUtility.waiter(() -> branchDetailsPage.pollPageUntilElementIsVisible(commitLink), equalTo(true));
        commitLink.click();
        branchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        branchDetailsPage.getPageNotFoundError().shouldNotBe(visible);
    }

    @Override
    public void createMergeRequest() {

    }

    @Override
    public void mergeMergeRequest() {

    }
}
