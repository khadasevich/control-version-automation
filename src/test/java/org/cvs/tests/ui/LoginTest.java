package org.cvs.tests.ui;

import io.qameta.allure.*;
import org.cvs.tests.ui.context.UIBaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cvs.core.config.Config.getUISteps;
import static org.cvs.utilities.AllureUtilities.UI_ALLURE_EPIC;

@Epic(UI_ALLURE_EPIC)
@Feature("AUTHENTICATION")
public class LoginTest extends UIBaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validate successfull login")
    public void testLogin() {
        assertThat(getUISteps().successfulLogIn()).as("Session cookie isn't set after login").isNotNull();
    }
}
