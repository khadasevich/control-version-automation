package org.cvs.tests.ui;

import io.qameta.allure.*;
import org.cvs.tests.ui.context.UIBaseLoginContext;
import org.cvs.utilities.GenerateTestData;
import org.testng.annotations.Test;

import static org.cvs.core.config.Config.*;
import static org.cvs.utilities.AllureUtilities.UI_ALLURE_EPIC;

@Epic(UI_ALLURE_EPIC)
@Feature("PULL REQUEST FEATURES")
public class PullRequestTest extends UIBaseLoginContext {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Create pull request to master and validate")
    void createPullRequestTest() {
        String prName = GenerateTestData.gitPRName();
        uiSteps.get().createPoolRequest(BRANCH_WITH_COMMIT_NAME, prName);
        uiSteps.get().validatePoolRequestCreation(prName);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Merge PR and validate")
    void mergePRTest() {
        uiSteps.get().mergePoolRequest(AUTOMATED_PR_TITLE);
        uiSteps.get().validateMerge(AUTOMATED_PR_TITLE);
    }
}
