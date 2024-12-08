package org.cvs.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.cvs.tests.context.UIBaseTest;
import org.testng.annotations.Test;

import static org.cvs.utilities.AllureReportUtility.UI_ALLURE_EPIC;

@Epic(UI_ALLURE_EPIC)
@Feature("COMMIT FEATURES")
public class CommitTest extends UIBaseTest {

    @Test
    @TmsLink("//ToDo")
    @Description("Add commit to the branch and validate")
    void addCommitTest() {
    }
}
