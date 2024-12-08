package org.cvs.pages.github;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class GithubPullRequestsPage extends GithubBasePage {

    public static final String PR_PAGE_PATH = "pulls";
    private SelenideElement mergePullRequestButton = $(new ByText("Merge pull request"));
    private SelenideElement confirmMRButton = $(new ByText("Confirm merge"));
}
