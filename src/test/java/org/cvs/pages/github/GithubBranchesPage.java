package org.cvs.pages.github;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class GithubBranchesPage extends GithubBasePage {

    public static final String BRANCHES_PAGE_PATH = "branches";
    private SelenideElement newBranchButton = $("[data-variant='primary'] [data-component='buttonContent']");
    private SelenideElement branchNameInput = $("[role='dialog'] [data-component='input']");
    private SelenideElement createNewBranchButton = $("[role='dialog'] [data-variant='primary']");
}
