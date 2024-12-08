package org.cvs.pages.github;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByTagAndText;
import com.codeborne.selenide.selector.ByText;
import com.codeborne.selenide.selector.ByTextCaseInsensitive;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class GithubBranchDetailsPage extends GithubBasePage {

    private SelenideElement addFileSelector = $(new ByTagAndText("span", "Add file"));
    private SelenideElement createNewFileButton = $(new ByText("Create new file"));
    private SelenideElement fileNameInput = $("[aria-label='File name']");
    private SelenideElement openCommitDialogButton = $("[data-testid='open-commit-dialog-button']");
    private SelenideElement submitCommitDialogButton = $("[data-testid='submit-commit-button']");
}
