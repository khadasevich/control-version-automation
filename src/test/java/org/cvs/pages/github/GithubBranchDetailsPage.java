package org.cvs.pages.github;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByTagAndText;
import com.codeborne.selenide.selector.ByText;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

@Data
public class GithubBranchDetailsPage extends GithubBasePage {

    private SelenideElement addFileSelector = $(new ByTagAndText("span", "Add file"));
    private SelenideElement createNewFileButton = $(new ByText("Create new file"));
    private SelenideElement fileNameInput = $("[aria-label='File name']");
    private SelenideElement openCommitDialogButton = $("[data-testid='open-commit-dialog-button']");
    private SelenideElement submitCommitDialogButton = $("[data-testid='submit-commit-button']");
    private SelenideElement compareAndPullButton = $(new ByTagAndText("span", "Compare & pull request"));
    private SelenideElement mrTitleInput = $(id("pull_request_title"));
    private SelenideElement createPullRequestButton = $(new ByTagAndText("span", "Create pull request"));

}
