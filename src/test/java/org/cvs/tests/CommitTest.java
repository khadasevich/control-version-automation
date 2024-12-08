package org.cvs.tests;

import io.qameta.allure.*;
import org.cvs.tests.context.UIBaseLoginContext;
import org.cvs.utilities.GenerateTestData;
import org.testng.annotations.Test;

import static org.cvs.core.config.Config.BRANCH_NAME;
import static org.cvs.utilities.AllureUtilities.UI_ALLURE_EPIC;

@Epic(UI_ALLURE_EPIC)
@Feature("COMMIT FEATURES")
public class CommitTest extends UIBaseLoginContext {

    private static final String COMMIT_FILE = GenerateTestData.gitCommitFileName();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Add commit to the branch and validate")
    void addCommitTest() {
        uiSteps.get().addCommit(BRANCH_NAME, COMMIT_FILE);
        uiSteps.get().validateCommitCreation(BRANCH_NAME, COMMIT_FILE);
    }
}
