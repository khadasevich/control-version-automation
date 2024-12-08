package org.cvs.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.cvs.tests.context.UIBaseTest;
import org.cvs.utilities.GenerateTestData;
import org.cvs.utilities.PollerUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.cvs.core.config.Config.getUISteps;
import static org.cvs.utilities.AllureReportUtility.UI_ALLURE_EPIC;
import static org.hamcrest.Matchers.equalTo;

@Epic(UI_ALLURE_EPIC)
@Feature("BRANCH FEATURES")
public class BranchTest extends UIBaseTest {

    private static final String TEST_BRANCH_NAME = GenerateTestData.branchName();

    @BeforeClass
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
    @TmsLink("//ToDo")
    @Description("Create new branch and validate")
    void createBranchTest() {
        uiSteps.get().createBranch(TEST_BRANCH_NAME);
        uiSteps.get().validateBranchCreation(TEST_BRANCH_NAME);
    }
}
