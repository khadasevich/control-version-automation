package org.cvs.tests.ui;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.cvs.tests.ui.context.UIBaseLoginContext;
import org.cvs.utilities.GenerateTestData;
import org.cvs.utilities.PollerUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.cvs.core.config.Config.getUISteps;
import static org.cvs.utilities.AllureUtilities.UI_ALLURE_EPIC;
import static org.hamcrest.Matchers.equalTo;

@Epic(UI_ALLURE_EPIC)
@Feature("BRANCH FEATURES")
public class BranchTest extends UIBaseLoginContext {

    private static final String TEST_BRANCH_NAME = GenerateTestData.branchName();

    @BeforeMethod
    public void loginAndGetCookie() {
        //Cookies login works only for branches, other tests require secured cookie that cannot be added
        uiSteps.set(getUISteps());
        PollerUtility.waiter(() -> userSession != null, equalTo(true));
        uiSteps.get().openRepositoryDetails();
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().addCookie(userSession);
        uiSteps.get().openRepositoryDetails();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Create new branch and validate")
    void createBranchTest() {
        uiSteps.get().createBranch(TEST_BRANCH_NAME);
        uiSteps.get().validateBranchCreation(TEST_BRANCH_NAME);
    }
}
