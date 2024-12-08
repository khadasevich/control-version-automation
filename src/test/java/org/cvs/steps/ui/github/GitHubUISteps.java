package org.cvs.steps.ui.github;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.cvs.pages.github.GithubBranchDetailsPage;
import org.cvs.pages.github.GithubBranchesPage;
import org.cvs.pages.github.GithubLoginPage;
import org.cvs.pages.github.GithubPullRequestsPage;
import org.cvs.steps.ui.UISteps;
import org.cvs.utilities.PollerUtility;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.cvs.core.config.Config.*;
import static org.cvs.pages.github.GithubBranchesPage.BRANCHES_PAGE_PATH;
import static org.cvs.pages.github.GithubLoginPage.LOGIN_PAGE_PATH;
import static org.cvs.pages.github.GithubPullRequestsPage.PR_PAGE_PATH;
import static org.cvs.tests.context.WebTimeouts.EXPLICIT_TIMEOUT;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class GitHubUISteps implements UISteps {

    private GithubLoginPage githubLoginPage = new GithubLoginPage();
    private GithubBranchesPage githubBranchesPage = new GithubBranchesPage();
    private GithubBranchDetailsPage githubBranchDetailsPage = new GithubBranchDetailsPage();
    private GithubPullRequestsPage githubPullRequestsPage = new GithubPullRequestsPage();

    @Step
    @Override
    public Cookie successfulLogIn() {
        open(LOGIN_PAGE_PATH);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        githubLoginPage.getUsernameInput().shouldBe(visible).sendKeys(USERNAME);
        githubLoginPage.getPasswordInput().shouldBe(visible).sendKeys(PASSWORD);
        githubLoginPage.getSignInButton().shouldBe(enabled).click();
        githubLoginPage.getHomePageIcon().shouldBe(visible);
        githubLoginPage.getAvatarIcon().shouldBe(visible);
        return WebDriverRunner.getWebDriver().manage().getCookieNamed("user_session");
    }

    @Step
    @Override
    public void openRepositoryDetails() {
        open(String.format("/%s/%s", USERNAME, DEFAULT_REPOSITORY_NAME));
    }

    @Step
    @Override
    public void createBranch(String branchName) {
        open(String.format("/%s/%s/%s", USERNAME, DEFAULT_REPOSITORY_NAME, BRANCHES_PAGE_PATH));
        githubBranchesPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        githubBranchesPage.getNewBranchButton().shouldBe(enabled).click();
        githubBranchesPage.getBranchNameInput().shouldBe(visible).sendKeys(branchName);
        githubBranchesPage.getCreateNewBranchButton().shouldBe(enabled).click();
        githubBranchesPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        log.info("Branch {} created", branchName);
    }

    @Step
    @Override
    public void validateBranchCreation(String branchName) {
        githubBranchesPage.getElementByTitle(branchName).shouldBe(visible, EXPLICIT_TIMEOUT).click();
        githubBranchesPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        githubBranchesPage.getPageNotFoundError().shouldNotBe(visible);
    }

    @Step
    private void openBranchDetails(String branchName) {
        open(String.format("/%s/%s/tree/%s", USERNAME, DEFAULT_REPOSITORY_NAME, branchName));
        githubBranchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
    }

    @Step
    @Override
    public void addCommit(String branchName, String fileName) {
        openBranchDetails(branchName);
        githubBranchDetailsPage.getAddFileSelector().shouldBe(enabled).click();
        githubBranchDetailsPage.getCreateNewFileButton().shouldBe(enabled).click();
        githubBranchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        githubBranchDetailsPage.getFileNameInput().sendKeys(fileName);
        githubBranchDetailsPage.getOpenCommitDialogButton().shouldBe(enabled).click();
        githubBranchDetailsPage.getSubmitCommitDialogButton().shouldBe(enabled).click();
        githubBranchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        log.info("Commit {} created", fileName);
    }

    @Step
    @Override
    public void validateCommitCreation(String branchName, String fileName) {
        openBranchDetails(branchName);
        SelenideElement commitLink = githubBranchDetailsPage.getElementByTitle(fileName);
        PollerUtility.waiter(() -> githubBranchDetailsPage.pollPageUntilElementIsVisible(commitLink), equalTo(true));
        commitLink.click();
        githubBranchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        githubBranchDetailsPage.getPageNotFoundError().shouldNotBe(visible);
    }

    @Step
    @Override
    public void createPoolRequest(String branchName, String prName) {
        openBranchDetails(branchName);
        githubBranchDetailsPage.getCompareAndPullButton().shouldBe(enabled).click();
        githubBranchDetailsPage.getMrTitleInput().clear();
        githubBranchDetailsPage.getMrTitleInput().sendKeys(prName);
        githubBranchDetailsPage.getCreatePullRequestButton().shouldBe(enabled).click();
        githubBranchDetailsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        log.info("Merge request {} created for branch {}", prName, branchName);
    }

    @Step
    private void openPullRequestsPage() {
        open(String.format("/%s/%s/%s", USERNAME, DEFAULT_REPOSITORY_NAME, PR_PAGE_PATH));
        githubPullRequestsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
    }

    @Step
    @Override
    public void validatePoolRequestCreation(String prName) {
        openPullRequestsPage();
        githubPullRequestsPage.getElementByText(prName).shouldBe(enabled).click();
        githubPullRequestsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        githubPullRequestsPage.getPageNotFoundError().shouldNotBe(visible);
    }

    @Step
    @Override
    public void mergePoolRequest(String prName) {
        openPullRequestsPage();
        githubPullRequestsPage.getElementByText(prName).shouldBe(enabled).click();
        githubPullRequestsPage.getProgressBar().shouldBe(hidden, EXPLICIT_TIMEOUT);
        githubPullRequestsPage.getMergePullRequestButton().shouldBe(enabled).click();
        githubPullRequestsPage.getConfirmMRButton().shouldBe(enabled).click();
        githubPullRequestsPage.getElementByText("Status: Merged");
        log.info("Branch {} merged", BRANCH_WITH_PR_NAME);
    }

    @Step
    @Override
    public void validateMerge(String prName) {
        openPullRequestsPage();
        SelenideElement mergedPR = githubPullRequestsPage.getElementByText(prName);
        PollerUtility.waiter(() -> githubBranchDetailsPage.pollPageUntilElementIsVisible(mergedPR), equalTo(false));
    }
}
