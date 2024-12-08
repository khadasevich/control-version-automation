package org.cvs.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.cvs.tests.context.UIBaseTest;
import org.cvs.utilities.GenerateTestData;
import org.testng.annotations.Test;

import static org.cvs.core.config.Config.BRANCH_NAME;
import static org.cvs.utilities.AllureReportUtility.UI_ALLURE_EPIC;

@Epic(UI_ALLURE_EPIC)
@Feature("COMMIT FEATURES")
public class CommitTest extends UIBaseTest {

    private static final String COMMIT_FILE = GenerateTestData.gitCommit();

    @Test
    @TmsLink("//ToDo")
    @Description("Add commit to the branch and validate")
    void addCommitTest() {
        uiSteps.get().addCommit(BRANCH_NAME, COMMIT_FILE);
        uiSteps.get().validateCommitCreation(BRANCH_NAME, COMMIT_FILE);
    }
}
