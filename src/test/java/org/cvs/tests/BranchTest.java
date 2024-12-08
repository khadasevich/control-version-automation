package org.cvs.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.cvs.core.config.Config;
import org.cvs.tests.context.UIBaseTest;
import org.cvs.utilities.GenerateTestData;
import org.testng.annotations.Test;

import static org.cvs.utilities.AllureReportUtility.UI_ALLURE_EPIC;

@Epic(UI_ALLURE_EPIC)
@Feature("BRANCH FEATURES")
public class BranchTest extends UIBaseTest {

    private final String TEST_BRANCH_NAME = GenerateTestData.branchName();

    @Test
    @TmsLink("//ToDo")
    @Description("Create new branch and validate")
    void createBranchTest() {
        uiSteps.createBranch(TEST_BRANCH_NAME);
        uiSteps.validateBranchCreation(TEST_BRANCH_NAME);
    }
}
