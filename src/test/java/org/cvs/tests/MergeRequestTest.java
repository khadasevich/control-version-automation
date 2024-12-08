package org.cvs.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.cvs.tests.context.UIBaseTest;
import org.testng.annotations.Test;

import static org.cvs.utilities.AllureReportUtility.UI_ALLURE_EPIC;

@Epic(UI_ALLURE_EPIC)
@Feature("MERGE REQUEST FEATURES")
public class MergeRequestTest extends UIBaseTest {

    @Test
    @TmsLink("//ToDo")
    @Description("Create merge request to master and validate")
    void createMergeRequestTest() {
    }

    @Test
    @TmsLink("//ToDo")
    @Description("Merge MR and validate")
    void mergeMRTest() {
    }
}
